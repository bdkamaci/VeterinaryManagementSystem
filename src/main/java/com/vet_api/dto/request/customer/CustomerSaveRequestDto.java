package com.vet_api.dto.request.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerSaveRequestDto {
    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String phone;

    @NonNull
    private String mail;

    @NonNull
    private String address;

    @NonNull
    private String city;
}
