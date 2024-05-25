package com.vet_api.repository;

import com.vet_api.entity.AvailableDate;
import com.vet_api.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("""
            select d from Doctor d inner join d.availableDates availableDates
            where d.id = ?1 and availableDates.availableDate = ?2""")
    Doctor findByIdAndAvailableDates_AvailableDate(long id, LocalDate availableDate);

    @Query("""
            select d from Doctor d inner join d.appointmentList appointmentList
            where d.id = ?1 and appointmentList.appointmentDate = ?2""")
    Doctor findByIdAndAppointmentList_AppointmentDate(long id, LocalDateTime appointmentDate);

    boolean existsByMailOrPhone(String mail, String phone);
}
