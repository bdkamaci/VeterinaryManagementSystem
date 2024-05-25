package com.vet_api.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentUpdateRequestDto {

    @NonNull
    private Long id;

    @NonNull
    private LocalDateTime appointmentDate;

    @NonNull
    private AppointmentAnimalRequest animal_id;

    @NonNull
    private AppointmentDoctorRequest doctor_id;

    @NonNull
    private AppointmentAvailableDateRequest available_date_id;

}
