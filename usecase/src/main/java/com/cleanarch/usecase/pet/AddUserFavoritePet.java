package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface AddUserFavoritePet {

  MessageResponseDTO add(Long petId);
}
