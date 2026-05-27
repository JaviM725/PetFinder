package com.royal.msvet.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "treatment")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Treatment {

    @Id
    @Column(name = "id", length = 50)
    String id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "date", nullable = false)
    LocalDate date;

    @Column(name = "notes", length = 500)
    String notes;

    @ManyToOne
    @JoinColumn(name = "administered_by")
    Veterinarian administeredBy;

    @ManyToOne
    @JoinColumn(name = "vaccination_card_id")
    VaccinationCard vaccinationCard;
}
