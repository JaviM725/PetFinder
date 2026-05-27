package com.royal.msvet.dto.request;

import com.royal.msvet.model.enums.MedicalEventAuthor;
import com.royal.msvet.model.enums.MedicalEventType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalEventRequestDTO {
    String id;
    String petProfileId;
    LocalDate date;
    MedicalEventType eventType;     // ACCIDENT, SURGERY, ILLNESS, ARRIVAL, OTHER
    String title;
    String description;
    String vetNotes;                // ← null si lo manda un ADOPTER
    String registeredById;
    MedicalEventAuthor registeredByRole;  // VET o ADOPTER
    String vaccinationCardId;
}
