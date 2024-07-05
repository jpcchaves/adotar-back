package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.*;

public interface DeletePetUseCase {
  MessageResponseDTO delete(Long petId);
}
