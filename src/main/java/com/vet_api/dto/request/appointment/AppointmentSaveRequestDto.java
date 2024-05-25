package com.vet_api.dto.request.appointment;

import com.vet_api.entity.Animal;
import com.vet_api.entity.AvailableDate;
import com.vet_api.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentSaveRequestDto {

    @NonNull
    private LocalDateTime appointmentDate;

    @NonNull
    private AppointmentAnimalRequest animal_id;

    @NonNull
    private AppointmentDoctorRequest doctor_id;

    @NonNull
    private AppointmentAvailableDateRequest available_date_id;
}
