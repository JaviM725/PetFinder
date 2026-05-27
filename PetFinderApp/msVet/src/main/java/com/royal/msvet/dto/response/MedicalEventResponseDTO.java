package com.royal.msvet.dto.response;

import com.royal.msvet.model.MedicalEvent;
import com.royal.msvet.model.enums.MedicalEventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalEventResponseDTO {

    String id;
    String petProfileId;
    LocalDate date;
    MedicalEventType eventType;
    String title;
    String description;
    String vetNotes;
    String registeredByRole;

    public static MedicalEventResponseDTO from(MedicalEvent event) {
        MedicalEventResponseDTO dto = new MedicalEventResponseDTO();
        dto.setId(event.getId());
        dto.setPetProfileId(event.getPetProfileId());
        dto.setDate(event.getDate());
        dto.setEventType(event.getEventType());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setVetNotes(event.getVetNotes());
        dto.setRegisteredByRole(event.getRegisteredByRole().name());
        return dto;
    }
}
