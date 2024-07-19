package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.dto.BasePetUpdateRequestDTO;

@FunctionalInterface
public interface UpdatePetUseCase {

  MessageResponseDTO update(
      Long petId,
      BasePetUpdateRequestDTO requestDTO
  );
}
