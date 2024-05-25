package com.vet_api.controller;

import com.vet_api.core.config.modelMapper.ModelMapperService;
import com.vet_api.dto.request.availableDate.AvailableDateSaveRequestDto;
import com.vet_api.dto.request.availableDate.AvailableDateUpdateRequestDto;
import com.vet_api.entity.AvailableDate;
import com.vet_api.service.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/available-date")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/save")
    public ResponseEntity addAvailableDate(@RequestBody AvailableDateSaveRequestDto availableDate) {
        return availableDateService.addAvailableDate(modelMapperService.forRequest().map(availableDate, AvailableDate.class));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findAvailableDate(@PathVariable Long id) {
        return availableDateService.findAvailableDate(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAvailableDate(@PathVariable Long id) {
        return availableDateService.deleteAvailableDate(id);
    }

    @PutMapping("/update")
    public ResponseEntity updateAvailableDate(@RequestBody AvailableDateUpdateRequestDto availableDate) {
        return availableDateService.updateAvailableDate(modelMapperService.forRequest().map(availableDate, AvailableDate.class));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        return availableDateService.findAll();
    }
}
