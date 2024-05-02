package com.jpcchaves.adotar.infrastructure.application.service.pet.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.pet.PetCharacteristicsDto;
import java.util.List;

public interface PetCharacteristicService {
  List<PetCharacteristicsDto> getAll();
}
