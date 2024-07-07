package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.DeletePetGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.DeletePetUseCase;

public class DeletePetUseCaseImpl implements DeletePetUseCase {

  private final DeletePetGateway deletePetGateway;

  public DeletePetUseCaseImpl(DeletePetGateway deletePetGateway) {
    this.deletePetGateway = deletePetGateway;
  }

  @Override
  public MessageResponseDTO delete(Long petId) {
    return deletePetGateway.delete(petId);
  }
}
