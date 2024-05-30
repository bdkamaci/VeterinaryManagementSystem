package com.vet_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_surname", nullable = false)
    private String surname;

    @Column(name = "customer_phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "customer_mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @Column(name = "customer_city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animals;
}
