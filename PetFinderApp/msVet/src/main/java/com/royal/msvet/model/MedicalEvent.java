package com.royal.msvet.model;

import com.royal.msvet.model.enums.MedicalEventAuthor;
import com.royal.msvet.model.enums.MedicalEventType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "medical_event")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalEvent {
    @Id
    @Column(name = "id", length = 50)
    String id;

    // Referencia al pet sin cruzar microservicios
    @Column(name = "pet_profile_id", nullable = false, length = 50)
    String petProfileId;

    @Column(name = "event_date", nullable = false)
    LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 30)
    MedicalEventType eventType;        // ← ACCIDENT, SURGERY, ILLNESS, ARRIVAL

    @Column(name = "title", nullable = false, length = 100)
    String title;

    @Column(name = "description", nullable = false, length = 500)
    String description;

    @Column(name = "vet_notes", length = 500)
    String vetNotes;                   // ← null si lo registró el adoptante

    // Quién lo registró
    @Column(name = "registered_by_id", nullable = false, length = 50)
    String registeredById;

    @Enumerated(EnumType.STRING)
    @Column(name = "registered_by_role", nullable = false, length = 20)
    MedicalEventAuthor registeredByRole;  // VET o ADOPTER

    // Relación con el carnet al que pertenece
    @ManyToOne
    @JoinColumn(name = "vaccination_card_id")
    VaccinationCard vaccinationCard;
}
