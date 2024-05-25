package com.vet_api.controller;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.request.doctor.DoctorSaveRequestDto;
import com.vet_api.dto.request.doctor.DoctorUpdateRequestDto;
import com.vet_api.entity.Doctor;
import com.vet_api.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addDoctor(@RequestBody DoctorSaveRequestDto doctor) {
        return doctorService.addDoctor(modelMapperService.forRequest().map(doctor, Doctor.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findDoctor(@PathVariable Long id) {
        return doctorService.findDoctor(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateDoctor(@RequestBody DoctorUpdateRequestDto doctor) {
        return doctorService.updateDoctor(modelMapperService.forRequest().map(doctor, Doctor.class));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllDoctors() {
        return doctorService.findAll();
    }


}
