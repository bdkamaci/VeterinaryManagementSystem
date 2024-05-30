package com.vet_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "available_dates")
@Getter
@Setter
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id")
    private long id;

    @Column(name = "available_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate availableDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "available_date_doctor_id", referencedColumnName = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToMany(mappedBy = "availableDate", cascade = {CascadeType.REMOVE,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointmentList;
}
