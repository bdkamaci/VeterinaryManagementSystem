package com.vet_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_name", nullable = false)
    private String name;

    @Column(name = "animal_specie", nullable = false)
    private String species;

    @Column(name = "animal_breed", nullable = false)
    private String breed;

    @Column(name = "animal_gender", nullable = false)
    private String gender;

    @Column(name = "animal_color", nullable = false)
    private String color;

    @Column(name = "animal_birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;
}
