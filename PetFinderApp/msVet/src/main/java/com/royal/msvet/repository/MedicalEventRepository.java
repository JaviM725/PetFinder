package com.royal.msvet.repository;

import com.royal.msvet.model.MedicalEvent;
import com.royal.msvet.model.enums.MedicalEventAuthor;
import com.royal.msvet.model.enums.MedicalEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalEventRepository extends JpaRepository<MedicalEvent, String> {

    //todos los eventos de una mascota
    List<MedicalEvent> findByPetProfileId(String petProfileId);

    // Filtrar por tipo
    List<MedicalEvent> findByPetProfileIdAndEventType(String petProfileId, MedicalEventType type);

    // Estadísticas por fecha para reportes del refugio
    List<MedicalEvent> findByPetProfileIdAndDateBetween(String petProfileId, LocalDate start, LocalDate end);

    // Separar eventos del vet vs del adoptante
    List<MedicalEvent> findByPetProfileIdAndRegisteredByRole(String petProfileId, MedicalEventAuthor role);
}
