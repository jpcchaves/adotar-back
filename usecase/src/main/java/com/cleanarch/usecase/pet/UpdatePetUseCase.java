package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.pet.dto.*;

public interface UpdatePetUseCase {
  MessageResponseDTO update(
      Long petId,
      BasePetUpdateRequestDTO requestDTO
  );
}
