package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface DeletePetGateway {

  MessageResponseDTO delete(Long petId);
}
