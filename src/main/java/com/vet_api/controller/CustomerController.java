package com.vet_api.controller;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.request.customer.CustomerSaveRequestDto;
import com.vet_api.dto.request.customer.CustomerUpdateRequestDto;
import com.vet_api.entity.Customer;
import com.vet_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addCustomer(@RequestBody CustomerSaveRequestDto customer) {
        return customerService.addCustomer(modelMapperService.forRequest().map(customer, Customer.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerUpdateRequestDto customer) {
        return customerService.updateCustomer(modelMapperService.forRequest().map(customer, Customer.class));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity findCustomerByName(@PathVariable String name) {
        return customerService.findCustomerByName(name);
    }

    @GetMapping("/byAnimalList/{id}")
    public ResponseEntity getByAnimalList(@PathVariable("id") Long id) {
        return customerService.getByAnimalList(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return customerService.findAll();
    }

}
