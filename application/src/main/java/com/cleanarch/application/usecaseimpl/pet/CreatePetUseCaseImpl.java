package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.*;
import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.pet.*;
import com.cleanarch.usecase.pet.dto.*;

public class CreatePetUseCaseImpl implements CreatePetUseCase {

  private final CreatePetGateway createPetGateway;

  public CreatePetUseCaseImpl(CreatePetGateway createPetGateway) {
    this.createPetGateway = createPetGateway;
  }

  @Override
  public MessageResponseDTO create(BasePetCreateRequestDTO requestDTO) {
    return createPetGateway.create(requestDTO);
  }
}
