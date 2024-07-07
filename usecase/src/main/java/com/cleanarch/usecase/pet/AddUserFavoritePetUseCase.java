package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface AddUserFavoritePetUseCase {

  MessageResponseDTO add(Long petId);
}
