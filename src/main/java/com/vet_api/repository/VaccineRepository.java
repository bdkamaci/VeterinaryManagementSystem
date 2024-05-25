package com.vet_api.repository;

import com.vet_api.entity.Animal;
import com.vet_api.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimal_Id(long id);
    List<Vaccine> findByNameAndCode(String name, String code);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate firstDate, LocalDate endDate);
    List<Vaccine> findByProtectionFinishDateAfterOrderByProtectionFinishDate(LocalDate endDate);
    Boolean existsVaccineByCodeAndNameAndAnimal_id(String code, String name, long id);

}
