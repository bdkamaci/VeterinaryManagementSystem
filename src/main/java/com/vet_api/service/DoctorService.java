package com.vet_api.service;

import com.vet_api.entity.Doctor;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    // Methods belonging to Doctor

    public ResponseEntity addDoctor(Doctor doctor);

    public ResponseEntity findDoctor(Long id);

    public ResponseEntity deleteDoctor(Long id);

    public ResponseEntity updateDoctor(Doctor doctor);

    public ResponseEntity findAll();
}
