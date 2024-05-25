package com.vet_api.repository;

import com.vet_api.entity.Animal;
import com.vet_api.entity.Appointment;
import com.vet_api.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByAppointmentDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByAppointmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByAppointmentDateBetweenAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);
    boolean existsByDoctor_Id(Long id);
    List<Appointment> findByAppointmentDateBetweenAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);
    boolean existsByAvailableDate_Id(Long id);

}

