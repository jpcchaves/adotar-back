package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.dto.BasePetCreateRequestDTO;

@FunctionalInterface
public interface CreatePetUseCase {

  MessageResponseDTO create(BasePetCreateRequestDTO requestDTO);
}
