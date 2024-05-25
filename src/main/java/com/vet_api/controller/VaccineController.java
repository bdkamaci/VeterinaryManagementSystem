package com.vet_api.controller;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.request.vaccine.VaccineSaveRequestDto;
import com.vet_api.dto.request.vaccine.VaccineUpdateRequestDto;
import com.vet_api.entity.Vaccine;
import com.vet_api.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vaccine")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addVaccine(@RequestBody VaccineSaveRequestDto vaccine) {
        return vaccineService.addVaccine(modelMapperService.forRequest().map(vaccine, Vaccine.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findVaccine(@PathVariable Long id) {
        return vaccineService.findVaccine(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVaccine(@PathVariable Long id) {
        return vaccineService.deleteVaccine(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateVaccine(@RequestBody VaccineUpdateRequestDto vaccine) {
        return vaccineService.updateVaccine(modelMapperService.forRequest().map(vaccine, Vaccine.class));
    }

    @GetMapping("/findByAnimal/{id}")
    public ResponseEntity findByAnimal_Id(@PathVariable Long id) {return vaccineService.findByAnimal_Id(id);}

    @GetMapping("/findAll")
    public ResponseEntity findAll() {return vaccineService.findALl();}

    @GetMapping("/expiring")
    public ResponseEntity getFilterByStartAndEndDate(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return vaccineService.getFilterByStartAndEndDate(startDate, endDate);
    }

}
