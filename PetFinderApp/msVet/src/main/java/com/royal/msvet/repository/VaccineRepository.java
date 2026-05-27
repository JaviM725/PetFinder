package com.royal.msvet.repository;

import com.royal.msvet.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, String> {
    // todas las vacunas de un carnet
    List<Vaccine> findByVaccinationCard_PetProfileId(String petProfileId);

    // próximas fechas de vacunación
    // busca vacunas cuya nextDueDate está entre hoy y una fecha límite
    List<Vaccine> findByNextDueDateBetween(LocalDate start, LocalDate end);

    // Quién aplicó cada vacuna
    List<Vaccine> findByAdministeredBy_Id(String veterinarianId);
}