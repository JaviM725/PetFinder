package com.royal.msvet.repository;

import com.royal.msvet.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, String> {

    // Todos los tratamientos de un carnet
    List<Treatment> findByVaccinationCard_PetProfileId(String petProfileId);

    // Tratamientos aplicados por un vet específico
    List<Treatment> findByAdministeredBy_Id(String veterinarianId);
}
