package com.vet_api.service.serviceImpl;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.response.appointment.AppointmentResponseDto;
import com.vet_api.entity.Animal;
import com.vet_api.entity.Appointment;
import com.vet_api.entity.AvailableDate;
import com.vet_api.entity.Doctor;
import com.vet_api.repository.AnimalRepository;
import com.vet_api.repository.AppointmentRepository;
import com.vet_api.repository.AvailableDateRepository;
import com.vet_api.repository.DoctorRepository;
import com.vet_api.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ModelMapperService modelMapperService;
    private final DoctorRepository doctorRepository;
    private final AvailableDateRepository availableDateRepository;
    private final AnimalRepository animalRepository;

    @Override
    public ResponseEntity addAppointment(Appointment appointment) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Appointment> hasAppointment = appointmentRepository.findById(appointment.getId());
        try {
            // Check if the specified doctor and animal exist
            if (!doctorRepository.existsById(appointment.getDoctor().getId()) || !animalRepository.existsById(appointment.getAnimal().getId())) {
                hashMap.put("Status", false);
                hashMap.put("Message", "ID Not Found!");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
            // Check if the doctor is available on the specified date
            if(availableDateRepository.findByAvailableDateIdInEndDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId()) == null){
                hashMap.put("Status", false);
                hashMap.put("Message", "Doctor is not available on the given date!");
                return new ResponseEntity<>(hashMap, HttpStatus.CONFLICT);
            }
            // Find the available date ID for the appointment's date and doctor
            Long availableId = availableDateRepository.findByAvailableDateIdInEndDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId());

            // Check if the available date exists for the specified date and doctor
            if (availableDateRepository.existsByIdAndAvailableDateAndDoctor_Id(availableId, appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId())) {
                // Check for appointment conflicts
                for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
                    if (appointmentRepository.existsByDoctor_Id(appointment.getDoctor().getId())) {
                        if (Duration.between(appointment.getAppointmentDate(), appointmentRepository.findAll().get(i).getAppointmentDate()).toHours() == 0) {
                            hashMap.put("Status", false);
                            hashMap.put("Message", "Appointment Conflict!");
                            return new ResponseEntity<>(hashMap, HttpStatus.CONFLICT);
                        }
                    }
                }
                //Associate the appointment with the available date and save it
                AvailableDate availableDate = availableDateRepository.findById(availableId).orElseThrow();
                appointment.setAvailableDate(availableDate);

                hashMap.put("Status", true);
                hashMap.put("Message", "Appointment Created!");
                AppointmentResponseDto result = modelMapperService.forResponse().map(appointmentRepository.save(appointment), AppointmentResponseDto.class);
                hashMap.put("Result", result);
                return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
            }
            //If no available date is found, throw a conflict exception
            hashMap.put("Status", false);
            hashMap.put("Message", "No Available Date is Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.CONFLICT);

        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Appointment could not be created!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findAppointment(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(appointment, AppointmentResponseDto.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Appointment Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteAppointment(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAppointment = appointmentRepository.existsById(id);
        try {
            if(hasAppointment) {
                appointmentRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Appointment has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Appointment Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Appointment could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateAppointment(Appointment appointment) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
        try {
            if(optionalAppointment.isPresent()) {
                appointmentRepository.saveAndFlush(appointment);
                hashMap.put("Status", true);
                hashMap.put("Message", "Appointment has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Appointment Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Appointment could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getAppointmentsByDateBetween(LocalDate startDate, LocalDate endDate) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
        List<AppointmentResponseDto> converted = new ArrayList<>();
        if(appointmentList != null){
            hashMap.put("Status", true);
            for(Appointment appointment : appointmentList){
                converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Related Doctor and Appointment Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentResponseDto> converted = new ArrayList<>();
        if(!appointments.isEmpty()) {
            hashMap.put("Status", true);
            for(Appointment appointment : appointments){
                converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponseDto.class));
            }
            hashMap.put("Result", converted);
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Appointments Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateBetweenAndDoctor(startDate, endDate, doctor);
        List<AppointmentResponseDto> converted = new ArrayList<>();
        if (doctorRepository.findById(doctor.getId()).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Doctor Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        if (!appointmentRepository.existsByAppointmentDateBetween(startDate, endDate)) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Appointment Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Appointment appointment : appointments) {
            converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponseDto.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateBetweenAndAnimal(startDate, endDate, animal);
        List<AppointmentResponseDto> converted = new ArrayList<>();
        if (animalRepository.findById(animal.getId()).isEmpty()) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Animal Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        if (!appointmentRepository.existsByAppointmentDateBetween(startDate, endDate)) {
            hashMap.put("Status", false);
            hashMap.put("Message", "Appointment Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Appointment appointment : appointments) {
            converted.add(modelMapperService.forResponse().map(appointment, AppointmentResponseDto.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
