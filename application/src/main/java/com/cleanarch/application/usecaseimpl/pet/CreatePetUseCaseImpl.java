package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.CreatePetGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.CreatePetUseCase;
import com.cleanarch.usecase.pet.dto.PetCreateRequestDTO;

public class CreatePetUseCaseImpl implements CreatePetUseCase {

  private final CreatePetGateway createPetGateway;

  public CreatePetUseCaseImpl(CreatePetGateway createPetGateway) {
    this.createPetGateway = createPetGateway;
  }

  @Override
  public MessageResponseDTO create(PetCreateRequestDTO requestDTO) {
    return createPetGateway.create(requestDTO);
  }
}
