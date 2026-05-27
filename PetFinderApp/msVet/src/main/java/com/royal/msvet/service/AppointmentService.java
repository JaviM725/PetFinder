package com.royal.msvet.service;

import com.royal.msvet.dto.request.AppointmentRequestDTO;
import com.royal.msvet.dto.response.AppointmentResponseDTO;
import com.royal.msvet.model.Appointment;
import com.royal.msvet.model.Veterinarian;
import com.royal.msvet.model.enums.AppointmentStatus;
import com.royal.msvet.repository.AppointmentRepository;
import com.royal.msvet.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final VeterinarianRepository veterinarianRepository;

    // US-14 — scheduleAppointment()
    public AppointmentResponseDTO schedule(AppointmentRequestDTO dto) {
        String id = requireId(dto.getId(), "Appointment id is required");
        if (appointmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Appointment id already exists: " + id);
        }
        Veterinarian vet = veterinarianRepository.findById(dto.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setAdopterId(dto.getAdopterId());
        appointment.setPetProfileId(dto.getPetProfileId());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setNotes(dto.getNotes());
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointment.setVeterinarian(vet);
        appointmentRepository.save(appointment);

        return toResponseDTO(appointment);
    }

    // Completar cita
    public AppointmentResponseDTO complete(String appointmentId) {
        return updateStatus(appointmentId, AppointmentStatus.COMPLETED);
    }

    // Cancelar cita
    public AppointmentResponseDTO cancel(String appointmentId) {
        return updateStatus(appointmentId, AppointmentStatus.CANCELLED);
    }

    // US-14 — agenda del vet
    public List<AppointmentResponseDTO> getScheduleByVet(String veterinarianId) {
        return appointmentRepository.findByVeterinarian_Id(veterinarianId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Citas pendientes del vet
    public List<AppointmentResponseDTO> getPendingByVet(String veterinarianId) {
        return appointmentRepository
                .findByVeterinarian_IdAndStatus(veterinarianId, AppointmentStatus.SCHEDULED)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private AppointmentResponseDTO updateStatus(String id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
        return toResponseDTO(appointment);
    }

    private AppointmentResponseDTO toResponseDTO(Appointment a) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setId(a.getId());
        dto.setAdopterId(a.getAdopterId());
        dto.setPetProfileId(a.getPetProfileId());
        dto.setVeterinarianId(a.getVeterinarian().getId());
        dto.setVeterinarianName(a.getVeterinarian().getName());
        dto.setAppointmentDate(a.getAppointmentDate());
        dto.setNotes(a.getNotes());
        dto.setStatus(a.getStatus());

        return dto;
    }

    private String requireId(String id, String message) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return id;
    }
}
