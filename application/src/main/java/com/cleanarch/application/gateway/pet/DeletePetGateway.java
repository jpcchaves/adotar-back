package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface DeletePetGateway {

  MessageResponseDTO delete(Long petId);
}
