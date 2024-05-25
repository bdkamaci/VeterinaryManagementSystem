package com.vet_api.dto.request.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorSaveRequestDto {
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
