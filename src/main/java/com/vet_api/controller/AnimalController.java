package com.vet_api.controller;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.request.animal.AnimalSaveRequestDto;
import com.vet_api.dto.request.animal.AnimalUpdateRequestDto;
import com.vet_api.entity.Animal;
import com.vet_api.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addAnimal(@RequestBody AnimalSaveRequestDto animal) {
        return animalService.addAnimal(modelMapperService.forRequest().map(animal, Animal.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findAnimal(@PathVariable Long id) {
        return animalService.findAnimal(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAnimal(@PathVariable Long id) {
        return animalService.deleteAnimal(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateAnimal(@RequestBody AnimalUpdateRequestDto animal) {
        return animalService.updateAnimal(modelMapperService.forRequest().map(animal, Animal.class));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        return animalService.findByName(name);
    }

    @GetMapping("/findByCustomerId/{id}")
    public ResponseEntity findByCustomer_Id(@PathVariable Long id) {
        return animalService.findByCustomer_Id(id);
    }

    @GetMapping("/findAnimalByCustomerName/{name}")
    public ResponseEntity findByAnimalByCustomerName(@PathVariable String name) {
        return animalService.getAnimalByCustomerName(name);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return animalService.findAll();
    }

}
