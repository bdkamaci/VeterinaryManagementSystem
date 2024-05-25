package com.vet_api.service.serviceImpl;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.response.vaccine.VaccineResponseDto;
import com.vet_api.entity.Vaccine;
import com.vet_api.repository.AnimalRepository;
import com.vet_api.repository.VaccineRepository;
import com.vet_api.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addVaccine(Vaccine vaccine) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> existingVaccines = vaccineRepository.findByNameAndCode(vaccine.getName(), vaccine.getCode());
        Optional<Vaccine> hasVaccine = vaccineRepository.findById(vaccine.getId());
        try {
            // Check if the specified animal exists in the repository
            if (animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Animal Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            // Check if a vaccine with the same code, name, and animal ID already exists
            if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
                // Check if there are any vaccines with an end date after the specified start date
                if(vaccineRepository.findByProtectionFinishDateAfterOrderByProtectionFinishDate(vaccine.getProtectionStartDate()).isEmpty()){// Section 22 - Vaccine day check
                    //  if the end date is before the start date
                    if (ChronoUnit.DAYS.between(vaccine.getProtectionStartDate(), vaccine.getProtectionFinishDate()) < 0) {
                        hashMap.put("Status", false);
                        hashMap.put("Message", "None Sense Exception - Bad Request!");
                        return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
                    }
                    // Save the vaccine if all checks pass
                    vaccineRepository.save(vaccine);
                    hashMap.put("Status", true);
                    hashMap.put("Message", "New vaccine added successfully.");
                    hashMap.put("Result", modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
                    return new ResponseEntity<>(hashMap, HttpStatus.OK);
                }
                // Throw an exception if there's a date mismatch
                hashMap.put("Status", false);
                hashMap.put("Message", "Date Mismatch!");
                return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
            }
            // Check if the end date is before the start date
            if (ChronoUnit.DAYS.between(vaccine.getProtectionStartDate(), vaccine.getProtectionFinishDate()) < 0) {
                hashMap.put("Status", false);
                hashMap.put("Message", "None Sense Exception - Bad Date!");
                return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
            }
            // Save the vaccine if all checks pass
            vaccineRepository.save(vaccine);
            hashMap.put("Status", true);
            hashMap.put("Message", "New vaccine added successfully.");
            hashMap.put("Result", modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.OK);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Vaccine could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findVaccine(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Vaccine vaccine = vaccineRepository.findById(id).orElse(null);
        if(vaccine != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Vaccine Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteVaccine(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasVaccine = vaccineRepository.existsById(id);
        try {
            if(hasVaccine) {
                vaccineRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Vaccine has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Vaccine Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Vaccine could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Vaccine getId(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity updateVaccine(Vaccine vaccine) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Vaccine> optionalVaccine = vaccineRepository.findById(vaccine.getId());
        try {
            // Verify the existence of the vaccine by ID
            getId(vaccine.getId());
            // Check if a vaccine with the same code, name, and animal ID already exists
            if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
                // Check if there are any vaccines with an end date after the specified start date
                if(vaccineRepository.findByProtectionFinishDateAfterOrderByProtectionFinishDate(vaccine.getProtectionStartDate()).isEmpty()){
                    // Check if the end date is before the start date
                    if (ChronoUnit.DAYS.between(vaccine.getProtectionStartDate(), vaccine.getProtectionFinishDate()) < 0) {
                        hashMap.put("Status", false);
                        hashMap.put("Message", "None Sense Exception - Bad Date!");
                        return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
                    }
                    // Save the updated vaccine if all checks pass
                    hashMap.put("Status", true);
                    hashMap.put("Message", "Vaccine has been updated!");
                    vaccineRepository.saveAndFlush(vaccine);
                    return new ResponseEntity<>(hashMap, HttpStatus.OK);
                }
            }
            // If the vaccine does not exist, add it as a new vaccine
            return addVaccine(vaccine);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Vaccine could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findByAnimal_Id(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccineList = vaccineRepository.findByAnimal_Id(id);
        List<VaccineResponseDto> converted = new ArrayList<>();
        if(vaccineList != null) {
            hashMap.put("Status", true);
            for(Vaccine vaccine : vaccineList) {
                converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findALl() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccines = vaccineRepository.findAll();
        List<VaccineResponseDto> converted = new ArrayList<>();
        try{
            if(vaccines.isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Record Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            hashMap.put("Status", true);
            for(Vaccine vaccine : vaccines) {
                converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
            }
            hashMap.put("Message", "Vaccines are being shown!");
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doctors could not be loaded!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    // Filter by Animal Vaccines
    @Override
    public ResponseEntity getAnimalVaccineList(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccines = vaccineRepository.findByAnimal_Id(id);
        List<VaccineResponseDto> converted = new ArrayList<>();
        if (vaccineRepository.findByAnimal_Id(id).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Animal Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Vaccine vaccine : vaccines) {
            converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
        }
        hashMap.put("Message", "Vaccines are being shown!");
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }

    // Filter By Protection End Date
    @Override
    public ResponseEntity getFilterByStartAndEndDate(LocalDate startDate, LocalDate endDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Vaccine> vaccines = vaccineRepository.findByProtectionFinishDateBetween(startDate,endDate);
        List<VaccineResponseDto> converted = new ArrayList<>();
        if (vaccineRepository.findByProtectionFinishDateBetween(startDate,endDate).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Vaccine Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Vaccine vaccine : vaccines) {
            converted.add(modelMapperService.forResponse().map(vaccine, VaccineResponseDto.class));
        }
        hashMap.put("Message", "Vaccines are being shown!");
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
