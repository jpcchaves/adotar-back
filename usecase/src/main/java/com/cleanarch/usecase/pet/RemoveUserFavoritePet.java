package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface RemoveUserFavoritePet {

  MessageResponseDTO remove(Long petId);
}
