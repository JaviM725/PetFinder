package com.royal.msvet.service;

import com.royal.msvet.dto.request.VeterinarianRequestDTO;
import com.royal.msvet.dto.response.VeterinarianResponseDTO;
import com.royal.msvet.model.Veterinarian;
import com.royal.msvet.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    // RF-05.1 — registrar vet en un refugio
    public VeterinarianResponseDTO register(VeterinarianRequestDTO dto) {
        String id = requireId(dto.getId(), "Veterinarian id is required");
        if (veterinarianRepository.existsById(id)) {
            throw new IllegalArgumentException("Veterinarian id already exists: " + id);
        }
        Veterinarian vet = new Veterinarian();
        vet.setId(id);
        vet.setName(dto.getName());
        vet.setSpecialty(dto.getSpecialty());
        vet.setPhoneNumber(dto.getPhoneNumber());
        vet.setEmail(dto.getEmail());
        vet.setShelterId(dto.getShelterId());
        veterinarianRepository.save(vet);
        return toResponseDTO(vet);
    }

    // RF-05.1 — todos los vets de un refugio
    public List<VeterinarianResponseDTO> getByShelter(String shelterId) {
        return veterinarianRepository.findByShelterId(shelterId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public VeterinarianResponseDTO getById(String id) {
        Veterinarian vet = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        return toResponseDTO(vet);
    }

    // RF-05.1 — actualizar datos del vet
    public VeterinarianResponseDTO update(String id, VeterinarianRequestDTO dto) {
        Veterinarian vet = veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        vet.setName(dto.getName());
        vet.setSpecialty(dto.getSpecialty());
        vet.setPhoneNumber(dto.getPhoneNumber());
        vet.setEmail(dto.getEmail());
        veterinarianRepository.save(vet);
        return toResponseDTO(vet);
    }

    private VeterinarianResponseDTO toResponseDTO(Veterinarian vet) {
        VeterinarianResponseDTO dto = new VeterinarianResponseDTO();
        dto.setId(vet.getId());
        dto.setName(vet.getName());
        dto.setSpecialty(vet.getSpecialty());
        dto.setPhoneNumber(vet.getPhoneNumber());
        dto.setEmail(vet.getEmail());
        dto.setShelterId(vet.getShelterId());
        return dto;
    }

    private String requireId(String id, String message) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return id;
    }
}
