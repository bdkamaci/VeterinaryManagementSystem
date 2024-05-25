package com.vet_api.service.serviceImpl;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.response.doctor.DoctorResponseDto;
import com.vet_api.entity.Doctor;
import com.vet_api.repository.DoctorRepository;
import com.vet_api.service.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addDoctor(Doctor doctor) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Doctor> hasDoctor = doctorRepository.findById(doctor.getId());
        try {
            if(doctorRepository.existsByMailOrPhone(doctor.getMail(),doctor.getPhone())){
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            doctorRepository.save(doctor);
            hashMap.put("Status", true);
            hashMap.put("Message", "Doctor Created!");
            hashMap.put("Result", modelMapperService.forResponse().map(doctor, DoctorResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doctor could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findDoctor(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if(doctor != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(doctor, DoctorResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Doctor Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteDoctor(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasDoctor = doctorRepository.existsById(id);
        try {
            if(hasDoctor) {
                doctorRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Doctor has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Doctor Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doctor could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateDoctor(Doctor doctor) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctor.getId());
        try {
            if(optionalDoctor.isPresent()) {
                doctorRepository.saveAndFlush(doctor);
                hashMap.put("Status", true);
                hashMap.put("Message", "Doctor has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Doctor Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doctor could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponseDto> converted = new ArrayList<>();
        try {
            if(doctors.isEmpty()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Records Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            hashMap.put("Status", true);
            for(Doctor doctor : doctors){
                converted.add(modelMapperService.forResponse().map(doctor, DoctorResponseDto.class));
            }
            hashMap.put("Message", "Doctors is been shown!");
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Doctors could not be loaded!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }
}
