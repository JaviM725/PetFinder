package com.royal.msvet.repository;

import com.royal.msvet.model.VaccinationCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCardRepository extends JpaRepository<VaccinationCard, String> {
    // La PK ya es el petProfileId, entonces findById() es suficiente
    // No necesita métodos extra
}
