package com.royal.msvet.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "vaccine")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vaccine {

    @Id
    @Column(name = "id", length = 50)
    String id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "applied_date", nullable = false)
    LocalDate appliedDate;

    @Column(name = "next_due_date")
    LocalDate nextDueDate;

    @ManyToOne
    @JoinColumn(name = "administered_by")
    Veterinarian administeredBy;        // ← quién la aplicó

    @ManyToOne
    @JoinColumn(name = "vaccination_card_id")
    VaccinationCard vaccinationCard;    // ← a qué carnet pertenece
}

