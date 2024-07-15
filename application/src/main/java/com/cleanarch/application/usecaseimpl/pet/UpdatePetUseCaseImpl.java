package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.*;
import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.pet.*;
import com.cleanarch.usecase.pet.dto.*;

public class UpdatePetUseCaseImpl implements UpdatePetUseCase {

  private final UpdatePetUseCaseGateway updatePetUseCaseGateway;

  public UpdatePetUseCaseImpl(UpdatePetUseCaseGateway updatePetUseCaseGateway) {
    this.updatePetUseCaseGateway = updatePetUseCaseGateway;
  }

  @Override
  public MessageResponseDTO update(
      Long petId,
      BasePetUpdateRequestDTO requestDTO
  ) {
    return updatePetUseCaseGateway.update(petId, requestDTO);
  }
}
