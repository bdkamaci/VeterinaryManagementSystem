package com.vet_api.dto.request.availableDate;

import com.vet_api.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvailableDateSaveRequestDto {
    @NonNull
    private LocalDate availableDate;

    @NonNull
    private AvailableDateDoctorRequest doctor_id;
}
