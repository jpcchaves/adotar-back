package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface RemoveUserFavoritePetGateway {

  MessageResponseDTO remove(Long petId);
}
