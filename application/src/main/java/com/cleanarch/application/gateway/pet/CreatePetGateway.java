package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.dto.PetCreateRequestDTO;

public interface CreatePetGateway {

  MessageResponseDTO create(PetCreateRequestDTO requestDTO);
}
