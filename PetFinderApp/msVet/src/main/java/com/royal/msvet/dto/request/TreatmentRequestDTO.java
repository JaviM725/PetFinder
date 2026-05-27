package com.royal.msvet.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreatmentRequestDTO {
    String id;
    String name;
    LocalDate date;
    String notes;
    String administeredById;
    String vaccinationCardId;
}
