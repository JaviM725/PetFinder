package com.royal.msvet.dto.response;

import com.royal.msvet.model.enums.AppointmentStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentResponseDTO {
    String id;
    String adopterId;
    String petProfileId;
    String veterinarianId;
    String veterinarianName;
    LocalDateTime appointmentDate;
    String notes;
    AppointmentStatus status;
}
