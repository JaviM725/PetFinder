package com.royal.msvet.dto.response;

import com.royal.msvet.model.Treatment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentResponseDTO {

    String id;
    String name;
    LocalDate date;
    String notes;
    String administeredByName;

    public static TreatmentResponseDTO from(Treatment treatment) {
        TreatmentResponseDTO dto = new TreatmentResponseDTO();
        dto.setId(treatment.getId());
        dto.setName(treatment.getName());
        dto.setDate(treatment.getDate());
        dto.setNotes(treatment.getNotes());
        dto.setAdministeredByName(
                treatment.getAdministeredBy() != null
                        ? treatment.getAdministeredBy().getName()
                        : null
        );
        return dto;
    }
}
