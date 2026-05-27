package com.royal.msvet.dto.response;

import com.royal.msvet.model.VaccinationCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCardResponseDTO {
    String petProfileId;
    List<VaccineResponseDTO> vaccines;
    List<TreatmentResponseDTO> treatments;
    List<MedicalEventResponseDTO> medicalEvents;
    LocalDate nextDueDate;

    public static VaccinationCardResponseDTO from(VaccinationCard card) {
        VaccinationCardResponseDTO dto = new VaccinationCardResponseDTO();
        dto.setPetProfileId(card.getPetProfileId());
        dto.setNextDueDate(card.getNextDueDate());
        dto.setVaccines(card.getVaccines().stream()
                .map(VaccineResponseDTO::from).toList());
        dto.setTreatments(card.getTreatments().stream()
                .map(TreatmentResponseDTO::from).toList());
        dto.setMedicalEvents(card.getMedicalEvents().stream()
                .map(MedicalEventResponseDTO::from).toList());
        return dto;
    }
}
