package com.vet_api.service.serviceImpl;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.response.availableDate.AvailableDateResponseDto;
import com.vet_api.entity.AvailableDate;
import com.vet_api.repository.AppointmentRepository;
import com.vet_api.repository.AvailableDateRepository;
import com.vet_api.repository.DoctorRepository;
import com.vet_api.service.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AvailableDateServiceImpl implements AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addAvailableDate(AvailableDate availableDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            if (doctorRepository.findById(availableDate.getDoctor().getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Doctor ID Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            if (availableDateRepository.existsByAvailableDateAndDoctor_Id(availableDate.getAvailableDate(), availableDate.getDoctor().getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            availableDateRepository.save(availableDate);
            hashMap.put("Status", true);
            hashMap.put("Message", "Available Date Created!");
            hashMap.put("Result", modelMapperService.forResponse().map(availableDate, AvailableDateResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Available Date could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findAvailableDate(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        AvailableDate availableDate = availableDateRepository.findById(id).orElse(null);
        if(availableDate != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(availableDate, AvailableDateResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Available Date Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteAvailableDate(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAvailableDate = availableDateRepository.existsById(id);
        try {
            if(hasAvailableDate) {
                availableDateRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Available Date has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Available Date Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Available Date could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateAvailableDate(AvailableDate availableDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<AvailableDate> optionalAvailableDate = availableDateRepository.findById(availableDate.getId());
        try {
            if (appointmentRepository.existsByAvailableDate_Id(availableDate.getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Appointment!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            //Check if the specified available date exists in the repository
            if(availableDateRepository.findById(availableDate.getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Available Date ID Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            //Check if the specified doctor exists in the repository
            if (doctorRepository.findById(availableDate.getDoctor().getId()).isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Doctor Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            // Check if the same date and doctor combination already exists in the repository
            if (availableDateRepository.existsByAvailableDateAndDoctor_Id(availableDate.getAvailableDate(), availableDate.getDoctor().getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            //Save the updated available date to the repository
            availableDateRepository.saveAndFlush(availableDate);
            hashMap.put("Status", true);
            hashMap.put("Message", "Available Date has been updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.OK);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Available Date could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<AvailableDate> availableDates = availableDateRepository.findAll();
        List<AvailableDateResponseDto> converted = new ArrayList<>();
        if(!availableDates.isEmpty()) {
            hashMap.put("Status", true);
            for(AvailableDate availableDate : availableDates) {
                converted.add(modelMapperService.forResponse().map(availableDate, AvailableDateResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Available Dates Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }
}
