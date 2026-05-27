package com.royal.msvet.controller;

import com.royal.msvet.dto.request.AppointmentRequestDTO;
import com.royal.msvet.dto.response.AppointmentResponseDTO;
import com.royal.msvet.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentRestController {

    private final AppointmentService appointmentService;

    // US-14 — scheduleAppointment()
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> schedule(
            @RequestBody AppointmentRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.schedule(dto));
    }

    // Completar cita
    @PatchMapping("/{id}/complete")
    public ResponseEntity<AppointmentResponseDTO> complete(
            @PathVariable String id) {
        return ResponseEntity.ok(appointmentService.complete(id));
    }

    // Cancelar cita
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponseDTO> cancel(
            @PathVariable String id) {
        return ResponseEntity.ok(appointmentService.cancel(id));
    }

    // US-14 — agenda completa del vet
    @GetMapping("/vet/{veterinarianId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getScheduleByVet(
            @PathVariable String veterinarianId) {
        return ResponseEntity.ok(appointmentService.getScheduleByVet(veterinarianId));
    }

    // Citas pendientes del vet
    @GetMapping("/vet/{veterinarianId}/pending")
    public ResponseEntity<List<AppointmentResponseDTO>> getPendingByVet(
            @PathVariable String veterinarianId) {
        return ResponseEntity.ok(appointmentService.getPendingByVet(veterinarianId));
    }
}
