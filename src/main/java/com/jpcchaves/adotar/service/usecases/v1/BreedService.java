package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.pet.BreedDto;

import java.util.List;

public interface BreedService {
    List<BreedDto> findAllByAnimalType(Long animalTypeId);
}
