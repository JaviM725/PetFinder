package com.royal.msvet.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {
    String id;
    String adopterId;
    String petProfileId;
    String veterinarianId;
    LocalDateTime appointmentDate;
    String notes;
}
