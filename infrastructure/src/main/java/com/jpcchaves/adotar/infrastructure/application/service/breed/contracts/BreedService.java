package com.jpcchaves.adotar.infrastructure.application.service.breed.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.pet.BreedDto;
import java.util.List;

public interface BreedService {
  List<BreedDto> findAllByAnimalType(Long animalTypeId);
}
