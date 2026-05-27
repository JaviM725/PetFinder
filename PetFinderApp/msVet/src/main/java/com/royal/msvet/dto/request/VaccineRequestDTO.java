package com.royal.msvet.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRequestDTO {
    String id;
    String name;
    LocalDate appliedDate;
    LocalDate nextDueDate;          // ← puede ser null si no requiere refuerzo
    String administeredById;        // ← ID del vet que la aplicó
    String vaccinationCardId;
}
