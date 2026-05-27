package com.royal.msvet.dto.request;

import lombok.Data;

// Solo necesita el ID del pet para crearse, el resto se agrega después
@Data
public class VaccinationCardRequestDTO {
    String petProfileId;            //shared primary key
}
