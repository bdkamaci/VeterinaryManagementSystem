package com.vet_api.dto.request.availableDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvailableDateUpdateRequestDto {
    @NonNull
    private Long id;

    @NonNull
    private LocalDate availableDate;

    @NonNull
    private AvailableDateDoctorRequest doctor_id;
}
