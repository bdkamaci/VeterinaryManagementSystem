package com.vet_api.service.serviceImpl;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.response.animal.AnimalResponseDto;
import com.vet_api.entity.Animal;
import com.vet_api.repository.AnimalRepository;
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
public class AnimalServiceImpl implements com.vet_api.service.AnimalService {

    private final AnimalRepository animalRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addAnimal(Animal animal) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Animal hasAnimal = animalRepository.findByNameAndSpeciesAndBreedAndGenderAndColorAndDateOfBirth(animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getGender(), animal.getColor(), animal.getDateOfBirth());
        try {
            if(hasAnimal != null) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            AnimalResponseDto result = modelMapperService.forResponse().map(animalRepository.save(animal), AnimalResponseDto.class);
            hashMap.put("Status", true);
            hashMap.put("Message", "Record Created!");
            hashMap.put("Result", result);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findAnimal(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Animal animal = animalRepository.findById(id).orElse(null);
        if(animal != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(animal, AnimalResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteAnimal(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAnimal = animalRepository.existsById(id);
        try {
            if(hasAnimal) {
                animalRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateAnimal(Animal animal) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Animal> optionalAnimal = animalRepository.findById(animal.getId());
        try {
            if(optionalAnimal.isPresent()) {
                animalRepository.saveAndFlush(animal);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findByName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animalList = animalRepository.findByName(name);
        List<AnimalResponseDto> converted = new ArrayList<>();
        if(animalList != null) {
            hashMap.put("Status", true);
            for(Animal animal : animalList){
                converted.add(modelMapperService.forResponse().map(animal, AnimalResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findByCustomer_Id(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animalList = animalRepository.findByCustomer_Id(id);
        List<AnimalResponseDto> converted = new ArrayList<>();
        if(animalList != null) {
            hashMap.put("Status", true);
            for(Animal animal : animalList){
                converted.add(modelMapperService.forResponse().map(animal, AnimalResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getAnimalByCustomerName(String name) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animalList = animalRepository.findByCustomerName(name);
        List<AnimalResponseDto> converted = new ArrayList<>();
        if(animalList.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Animal animal : animalList){
            converted.add(modelMapperService.forResponse().map(animal, AnimalResponseDto.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Animal> animals = animalRepository.findAll();
        List<AnimalResponseDto> converted = new ArrayList<>();
        if(animals.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Animal animal : animals){
            converted.add( modelMapperService.forResponse().map(animal, AnimalResponseDto.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}

