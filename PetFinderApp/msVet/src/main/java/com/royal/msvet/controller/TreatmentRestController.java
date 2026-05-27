package com.royal.msvet.controller;

import com.royal.msvet.dto.request.TreatmentRequestDTO;
import com.royal.msvet.dto.response.TreatmentResponseDTO;
import com.royal.msvet.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/treatments")
@RequiredArgsConstructor
public class TreatmentRestController {

    private final TreatmentService treatmentService;

    // addDeworming() / tratamiento general
    @PostMapping
    public ResponseEntity<TreatmentResponseDTO> addTreatment(
            @RequestBody TreatmentRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(treatmentService.addTreatment(dto));
    }

    // Todos los tratamientos de una mascota
    @GetMapping("/pet/{petProfileId}")
    public ResponseEntity<List<TreatmentResponseDTO>> getByPet(
            @PathVariable String petProfileId) {
        return ResponseEntity.ok(treatmentService.getByPet(petProfileId));
    }
}
