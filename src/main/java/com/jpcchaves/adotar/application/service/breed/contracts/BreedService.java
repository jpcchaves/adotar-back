package com.jpcchaves.adotar.application.service.breed.contracts;

import com.jpcchaves.adotar.application.dto.pet.BreedDto;

import java.util.List;

public interface BreedService {
  List<BreedDto> findAllByAnimalType(Long animalTypeId);
}
