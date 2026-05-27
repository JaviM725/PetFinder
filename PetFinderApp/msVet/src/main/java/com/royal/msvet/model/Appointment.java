package com.royal.msvet.model;

import com.royal.msvet.model.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {

    @Id
    @Column(name = "id", length = 50)
    String id;

    // Referencias sin cruzar microservicios
    @Column(name = "adopter_id", nullable = false, length = 50)
    String adopterId;                       // ← vive en adopter-service

    @Column(name = "pet_profile_id", nullable = false, length = 50)
    String petProfileId;                    // ← vive en pet-service

    @Column(name = "appointment_date", nullable = false)
    LocalDateTime appointmentDate;

    @Column(name = "notes", length = 500)
    String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    AppointmentStatus status;               // SCHEDULED, COMPLETED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    Veterinarian veterinarian;
}

