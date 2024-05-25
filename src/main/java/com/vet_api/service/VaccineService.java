package com.vet_api.service;

import com.vet_api.entity.Vaccine;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface VaccineService {
    // Methods belonging to Vaccine

    public ResponseEntity addVaccine(Vaccine vaccine);

    public ResponseEntity findVaccine(Long id);

    public ResponseEntity deleteVaccine(Long id);

    public ResponseEntity updateVaccine(Vaccine vaccine);

    public ResponseEntity findByAnimal_Id(Long id);

    public ResponseEntity findALl();

    Vaccine getId(Long id);

    ResponseEntity getAnimalVaccineList(Long id);

    ResponseEntity getFilterByStartAndEndDate(LocalDate startDate,LocalDate endDate);
}
