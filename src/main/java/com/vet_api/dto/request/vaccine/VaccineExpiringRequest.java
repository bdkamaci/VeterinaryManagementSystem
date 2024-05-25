package com.vet_api.dto.request.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccineExpiringRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
