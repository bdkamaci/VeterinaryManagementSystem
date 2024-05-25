package com.vet_api.dto.request.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnimalUpdateRequestDto {
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String species;

    @NonNull
    private String breed;

    @NonNull
    private String gender;

    @NonNull
    private String color;

    @NonNull
    private LocalDate dateOfBirth;

    @NonNull
    private AnimalCustomerRequest customer_id;
}
