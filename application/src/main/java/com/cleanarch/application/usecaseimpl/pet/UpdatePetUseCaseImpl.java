package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.UpdatePetUseCaseGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.UpdatePetUseCase;
import com.cleanarch.usecase.pet.dto.PetUpdateRequestDTO;

public class UpdatePetUseCaseImpl implements UpdatePetUseCase {

  private final UpdatePetUseCaseGateway updatePetUseCaseGateway;

  public UpdatePetUseCaseImpl(UpdatePetUseCaseGateway updatePetUseCaseGateway) {
    this.updatePetUseCaseGateway = updatePetUseCaseGateway;
  }

  @Override
  public MessageResponseDTO update(
      Long petId,
      PetUpdateRequestDTO requestDTO
  ) {
    return updatePetUseCaseGateway.update(petId, requestDTO);
  }
}
