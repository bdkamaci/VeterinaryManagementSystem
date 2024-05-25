package com.vet_api.dto.response.availableDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvailableDateResponseDto {
    private long id;
    private LocalDate availableDate;
    private AvailableDateDoctorResponse doctor;
}
