package com.vet_api.dto.response.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private CustomerAnimalResponse animals;
}
