package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.dto.BasePetUpdateRequestDTO;

@FunctionalInterface
public interface UpdatePetUseCaseGateway {

  MessageResponseDTO update(
      Long petId,
      BasePetUpdateRequestDTO requestDTO
  );
}
