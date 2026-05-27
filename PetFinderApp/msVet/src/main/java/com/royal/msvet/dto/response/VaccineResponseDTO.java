package com.royal.msvet.dto.response;

import com.royal.msvet.model.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineResponseDTO {

    String id;
    String name;
    LocalDate appliedDate;
    LocalDate nextDueDate;
    String administeredByName;

    public static VaccineResponseDTO from(Vaccine vaccine) {
        VaccineResponseDTO dto = new VaccineResponseDTO();
        dto.setId(vaccine.getId());
        dto.setName(vaccine.getName());
        dto.setAppliedDate(vaccine.getAppliedDate());
        dto.setNextDueDate(vaccine.getNextDueDate());
        dto.setAdministeredByName(
                vaccine.getAdministeredBy() != null
                        ? vaccine.getAdministeredBy().getName()
                        : null
        );
        return dto;
    }
}
