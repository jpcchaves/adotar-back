package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface RemoveUserFavoritePetGateway {

  MessageResponseDTO remove(Long petId);
}
