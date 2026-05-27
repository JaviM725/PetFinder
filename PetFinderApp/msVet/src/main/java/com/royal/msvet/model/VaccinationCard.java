package com.royal.msvet.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaccination_card")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VaccinationCard {

    @Id
    @Column(name = "pet_profile_id", length = 50)
    String petProfileId;

    @OneToMany(mappedBy = "vaccinationCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<Vaccine> vaccines = new ArrayList<>();       // entidad completa

    @OneToMany(mappedBy = "vaccinationCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<Treatment> treatments = new ArrayList<>();   // entidad completa

    // getNextDueDate() busca la fecha más próxima entre todas las vacunas
    public LocalDate getNextDueDate() {
        return vaccines.stream()
                .map(Vaccine::getNextDueDate)
                .filter(date -> date != null && date.isAfter(LocalDate.now()))
                .min(LocalDate::compareTo)
                .orElse(null);
    }

    @OneToMany(mappedBy = "vaccinationCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<MedicalEvent> medicalEvents = new ArrayList<>();
}
