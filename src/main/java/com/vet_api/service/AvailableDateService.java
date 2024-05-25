package com.vet_api.service;

import com.vet_api.entity.AvailableDate;
import org.springframework.http.ResponseEntity;

public interface AvailableDateService {
    // Methods belonging to AvailableDate

    public ResponseEntity addAvailableDate(AvailableDate availableDate);

    public ResponseEntity findAvailableDate(Long id);

    public ResponseEntity deleteAvailableDate(Long id);

    public ResponseEntity updateAvailableDate(AvailableDate availableDate);

    public ResponseEntity findAll();

}
