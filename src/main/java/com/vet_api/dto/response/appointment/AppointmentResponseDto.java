package com.vet_api.dto.response.appointment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentDate;
    private AppointmentAnimalResponse animal;
    private AppointmentDoctorResponse doctor;
}
