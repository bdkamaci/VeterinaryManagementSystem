package com.vet_api.service;

import com.vet_api.entity.Animal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnimalService {
    // Methods belonging to Animal

    public ResponseEntity addAnimal(Animal animal);

    public ResponseEntity findAnimal(Long id);

    public ResponseEntity deleteAnimal(Long id);

    public ResponseEntity updateAnimal(Animal animal);

    public ResponseEntity findByName(String name);

    public ResponseEntity findByCustomer_Id(Long id);

    public ResponseEntity getAnimalByCustomerName(String name);

    public ResponseEntity findAll();

}
