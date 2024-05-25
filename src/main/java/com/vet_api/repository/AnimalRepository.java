package com.vet_api.repository;

import com.vet_api.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {


    List<Animal> findByName(String name);
    List<Animal> findByCustomer_Id(Long id);
    List<Animal> findByCustomerName(String name);
    Animal findByNameAndSpeciesAndBreedAndGenderAndColorAndDateOfBirth(String name, String species, String breed, String gender, String color, LocalDate dateOfBirth);

}
