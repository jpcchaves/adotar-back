package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.pet.dto.*;

public interface UpdatePetUseCaseGateway {

  MessageResponseDTO update(
      Long petId,
      BasePetUpdateRequestDTO requestDTO
  );
}
