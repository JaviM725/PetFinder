package com.royal.msvet.service;

import com.royal.msvet.dto.request.TreatmentRequestDTO;
import com.royal.msvet.dto.response.TreatmentResponseDTO;
import com.royal.msvet.model.Treatment;
import com.royal.msvet.model.VaccinationCard;
import com.royal.msvet.model.Veterinarian;
import com.royal.msvet.repository.TreatmentRepository;
import com.royal.msvet.repository.VaccinationCardRepository;
import com.royal.msvet.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final VaccinationCardRepository vaccinationCardRepository;
    private final VeterinarianRepository veterinarianRepository;

    // US-17 — addDeworming() / tratamiento general
    public TreatmentResponseDTO addTreatment(TreatmentRequestDTO dto) {
        String id = requireId(dto.getId(), "Treatment id is required");
        if (treatmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Treatment id already exists: " + id);
        }
        VaccinationCard card = vaccinationCardRepository.findById(dto.getVaccinationCardId())
                .orElseThrow(() -> new RuntimeException("Carnet no encontrado"));

        Veterinarian vet = veterinarianRepository.findById(dto.getAdministeredById())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        Treatment treatment = new Treatment();
        treatment.setId(id);
        treatment.setName(dto.getName());
        treatment.setDate(dto.getDate());
        treatment.setNotes(dto.getNotes());
        treatment.setAdministeredBy(vet);
        treatment.setVaccinationCard(card);
        treatmentRepository.save(treatment);

        return TreatmentResponseDTO.from(treatment);
    }

    public List<TreatmentResponseDTO> getByPet(String petProfileId) {
        return treatmentRepository.findByVaccinationCard_PetProfileId(petProfileId)
                .stream()
                .map(TreatmentResponseDTO::from)
                .toList();
    }

    private String requireId(String id, String message) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return id;
    }
}
