package com.royal.msvet.controller;

import com.royal.msvet.dto.request.VaccinationCardRequestDTO;
import com.royal.msvet.dto.response.VaccinationCardResponseDTO;
import com.royal.msvet.service.VaccinationCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaccination-cards")
@RequiredArgsConstructor

public class VaccinationCardRestController {

    private final VaccinationCardService vaccinationCardService;

    // Se llama desde pet-service cuando se crea un pet
    // RF-01.2 — creación automática del carnet
    @PostMapping
    public ResponseEntity<VaccinationCardResponseDTO> create(
            @RequestBody VaccinationCardRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vaccinationCardService.create(dto));
    }

    // US-17 — consultar carnet completo por ID del pet
    @GetMapping("/{petProfileId}")
    public ResponseEntity<VaccinationCardResponseDTO> getByPetId(
            @PathVariable String petProfileId) {
        return ResponseEntity.ok(vaccinationCardService.getByPetId(petProfileId));
    }

    // US-17 — isVaccinationCurrent()
    @GetMapping("/{petProfileId}/current")
    public ResponseEntity<Boolean> isVaccinationCurrent(
            @PathVariable String petProfileId) {
        return ResponseEntity.ok(vaccinationCardService.isVaccinationCurrent(petProfileId));
    }
}
