package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface RemoveUserFavoritePetUseCase {

  MessageResponseDTO remove(Long petId);
}
