package com.vet_api.dto.request.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VaccineSaveRequestDto {
    @NonNull
    private String name;
    @NonNull
    private String code;
    @NonNull
    private LocalDate protectionStartDate;
    @NonNull
    private LocalDate protectionFinishDate;
    @NonNull
    private VaccineAnimalRequest animal_id;
}
