package com.vet_api.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentByDateRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
