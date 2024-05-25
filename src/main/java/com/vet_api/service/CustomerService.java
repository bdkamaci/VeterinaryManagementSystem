package com.vet_api.service;

import com.vet_api.entity.Animal;
import com.vet_api.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    // Methods belonging to Customer

    public ResponseEntity addCustomer(Customer customer);

    public ResponseEntity findCustomer(Long id);

    public ResponseEntity deleteCustomer(Long id);

    public ResponseEntity updateCustomer(Customer customer);

    public ResponseEntity findCustomerByName(String name);

    public ResponseEntity findAll();

    Customer getId(Long id);
    ResponseEntity getByAnimalList(Long id);
}
