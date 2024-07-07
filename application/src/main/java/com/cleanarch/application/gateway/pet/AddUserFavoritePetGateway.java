package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface AddUserFavoritePetGateway {

  MessageResponseDTO add(Long petId);
}
