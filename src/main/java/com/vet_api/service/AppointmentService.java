package com.vet_api.service;

import com.vet_api.entity.Animal;
import com.vet_api.entity.Appointment;
import com.vet_api.entity.Doctor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    // Methods belonging to Appointment

    public ResponseEntity addAppointment(Appointment appointment);

    public ResponseEntity findAppointment(Long id);

    public ResponseEntity deleteAppointment(Long id);

    public ResponseEntity updateAppointment(Appointment appointment);

    public ResponseEntity getAppointmentsByDateBetween(LocalDate startDate, LocalDate endDate);

    public ResponseEntity findAll();

    public ResponseEntity filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);
    public ResponseEntity filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);
}
