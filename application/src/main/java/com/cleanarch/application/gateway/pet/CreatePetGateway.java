package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.pet.dto.*;

public interface CreatePetGateway {

  MessageResponseDTO create(BasePetCreateRequestDTO requestDTO);
}
