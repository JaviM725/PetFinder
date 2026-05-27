package com.royal.msvet.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class VeterinarianResponseDTO {
    String id;
    String name;
    String specialty;
    String phoneNumber;
    String email;
    String shelterId;
    List<AppointmentResponseDTO> schedule;
}
