package com.royal.msvet.repository;

import com.royal.msvet.model.Appointment;
import com.royal.msvet.model.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    // Agenda de un vet — US-14
    List<Appointment> findByVeterinarian_Id(String veterinarianId);

    // Citas de un adoptante específico
    List<Appointment> findByAdopterId(String adopterId);

    // Citas de una mascota específica
    List<Appointment> findByPetProfileId(String petProfileId);

    // Citas pendientes de un vet
    List<Appointment> findByVeterinarian_IdAndStatus(String veterinarianId,
                                                     AppointmentStatus status);

    // Citas en un rango de fechas
    List<Appointment> findByAppointmentDateBetween(LocalDateTime start,
                                                   LocalDateTime end);
}
