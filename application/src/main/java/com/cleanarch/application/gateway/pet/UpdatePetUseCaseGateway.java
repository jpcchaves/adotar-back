package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.dto.PetUpdateRequestDTO;

public interface UpdatePetUseCaseGateway {

  MessageResponseDTO update(
      Long petId,
      PetUpdateRequestDTO requestDTO
  );
}
