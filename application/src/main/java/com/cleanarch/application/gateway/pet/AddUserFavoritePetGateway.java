package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface AddUserFavoritePetGateway {

  MessageResponseDTO add(Long petId);
}
