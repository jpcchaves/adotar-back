package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.pet.PetCharacteristicsDto;

import java.util.List;

public interface PetCharacteristicService {
    List<PetCharacteristicsDto> getAll();
}
