package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.dto.BasePetCreateRequestDTO;

@FunctionalInterface
public interface CreatePetGateway {

  MessageResponseDTO create(BasePetCreateRequestDTO requestDTO);
}
