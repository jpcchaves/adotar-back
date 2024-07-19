package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface AddUserFavoritePetUseCase {

  MessageResponseDTO add(Long petId);
}
