package com.vet_api.service.serviceImpl;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.response.customer.CustomerResponseDto;
import com.vet_api.entity.Animal;
import com.vet_api.entity.Customer;
import com.vet_api.repository.CustomerRepository;
import com.vet_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addCustomer(Customer customer) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Customer> hasCustomer = customerRepository.findById(customer.getId());
        try {
            if(hasCustomer.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Customer!");
                hashMap.put("Customer ID", customer.getId());
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            customerRepository.save(customer);
            hashMap.put("Status", true);
            hashMap.put("Message", "Customer Created!");
            hashMap.put("Result", modelMapperService.forResponse().map(customer, CustomerResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Customer could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findCustomer(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(customer, CustomerResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Customer Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteCustomer(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasCustomer = customerRepository.existsById(id);
        try {
            if(hasCustomer) {
                customerRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Customer has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Customer could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateCustomer(Customer customer) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        try {
            if(optionalCustomer.isPresent()) {
                customerRepository.saveAndFlush(customer);
                hashMap.put("Status", true);
                hashMap.put("Message", "Customer has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Customer could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findCustomerByName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Customer> customerList = customerRepository.findByName(name);
        List<CustomerResponseDto> converted = new ArrayList<>();
        if(!customerList.isEmpty()) {
            hashMap.put("Status", true);
            for(Customer customer : customerList) {
                converted.add(modelMapperService.forResponse().map(customer, CustomerResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Customer(s) Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> converted = new ArrayList<>();
        if(!customers.isEmpty()) {
            hashMap.put("Status", true);
            for(Customer customer : customers){
                converted.add(modelMapperService.forResponse().map(customer, CustomerResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Customer(s) Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public Customer getId(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity getByAnimalList(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if(customerRepository.findById(id).isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        if(getId(id).getAnimals().isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Customer Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        hashMap.put("Result", getId(id).getAnimals());
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
