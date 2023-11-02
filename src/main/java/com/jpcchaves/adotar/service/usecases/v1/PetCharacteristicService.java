package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.pet.PetCharacteristicsDto;

import java.util.List;

public interface PetCharacteristicService {
    List<PetCharacteristicsDto> getAll();
}
