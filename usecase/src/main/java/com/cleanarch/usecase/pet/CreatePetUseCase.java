package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.pet.dto.*;

public interface CreatePetUseCase {
  MessageResponseDTO create(PetCreateRequestDTO requestDTO);
}
