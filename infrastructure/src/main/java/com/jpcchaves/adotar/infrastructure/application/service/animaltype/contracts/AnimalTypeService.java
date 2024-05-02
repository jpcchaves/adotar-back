package com.jpcchaves.adotar.infrastructure.application.service.animaltype.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.pet.AnimalTypeMinDto;
import java.util.List;

public interface AnimalTypeService {
  List<AnimalTypeMinDto> getAll();

  AnimalTypeMinDto getById(Long id);
}
