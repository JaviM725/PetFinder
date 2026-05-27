package com.royal.msvet.service;

import com.royal.msvet.dto.request.VaccinationCardRequestDTO;
import com.royal.msvet.dto.response.VaccinationCardResponseDTO;
import com.royal.msvet.model.VaccinationCard;
import com.royal.msvet.repository.VaccinationCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationCardService {

    private final VaccinationCardRepository vaccinationCardRepository;

    // Se llama desde pet-service via WebClient cuando se crea un pet
    // RF-01.2 — carnet creado automáticamente con el mismo ID del pet
    public VaccinationCardResponseDTO create(VaccinationCardRequestDTO dto) {
        // Verifica que no exista ya uno con ese ID
        if (vaccinationCardRepository.existsById(dto.getPetProfileId())) {
            throw new RuntimeException("Ya existe un carnet para esta mascota");
        }
        VaccinationCard card = new VaccinationCard();
        card.setPetProfileId(dto.getPetProfileId());  // ← shared primary key
        vaccinationCardRepository.save(card);
        return VaccinationCardResponseDTO.from(card);
    }

    // US-17 — consultar carnet completo
    public VaccinationCardResponseDTO getByPetId(String petProfileId) {
        VaccinationCard card = vaccinationCardRepository.findById(petProfileId)
                .orElseThrow(() -> new RuntimeException("Carnet no encontrado"));
        return VaccinationCardResponseDTO.from(card);
    }

    // US-17 — isVaccinationCurrent()
    // verifica si todas las vacunas están al día
    public boolean isVaccinationCurrent(String petProfileId) {
        VaccinationCard card = vaccinationCardRepository.findById(petProfileId)
                .orElseThrow(() -> new RuntimeException("Carnet no encontrado"));
        return card.getVaccines().stream()
                .allMatch(v -> v.getNextDueDate() == null
                        || v.getNextDueDate().isAfter(java.time.LocalDate.now()));
    }
}
