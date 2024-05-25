package com.vet_api.controller;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.request.appointment.AppointmentByDateRequest;
import com.vet_api.dto.request.appointment.AppointmentSaveRequestDto;
import com.vet_api.dto.request.appointment.AppointmentUpdateRequestDto;
import com.vet_api.entity.Animal;
import com.vet_api.entity.Appointment;

import com.vet_api.entity.Doctor;
import com.vet_api.repository.AnimalRepository;
import com.vet_api.repository.DoctorRepository;
import com.vet_api.service.AnimalService;
import com.vet_api.service.AppointmentService;
import com.vet_api.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ModelMapperService modelMapperService;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    @PostMapping("/save")
    public ResponseEntity addAppointment(@RequestBody AppointmentSaveRequestDto appointment) {
        return appointmentService.addAppointment(modelMapperService.forRequest().map(appointment, Appointment.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findAppointment(@PathVariable Long id) {
        return appointmentService.findAppointment(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateAppointment(@RequestBody AppointmentUpdateRequestDto appointment) {
        return appointmentService.updateAppointment(modelMapperService.forRequest().map(appointment, Appointment.class));
    }

    @GetMapping("/dateDoctor")
    public ResponseEntity filterDateTimeAndDoctor(@RequestParam("startDate") LocalDateTime startDate,
                                                  @RequestParam("endDate") LocalDateTime endDate,
                                                  @RequestParam("doctorId") Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        return appointmentService.filterDateTimeAndDoctor(startDate, endDate, doctor);
    }


    @GetMapping("/dateAnimal")
    public ResponseEntity filterDateTimeAndAnimal(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                            @RequestParam("animalId") Long animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);
        return appointmentService.filterDateTimeAndAnimal(startDate, endDate, animal);
    }

    @GetMapping("/findAll")
    public ResponseEntity findALL() {
        return appointmentService.findAll();
    }
}
