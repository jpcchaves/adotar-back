package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.RemoveUserFavoritePetGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.RemoveUserFavoritePetUseCase;

public class RemoveUserFavoritePetUseCaseImpl implements
    RemoveUserFavoritePetUseCase {

  private final RemoveUserFavoritePetGateway removeUserFavoritePetGateway;

  public RemoveUserFavoritePetUseCaseImpl(RemoveUserFavoritePetGateway removeUserFavoritePetGateway) {
    this.removeUserFavoritePetGateway = removeUserFavoritePetGateway;
  }

  @Override
  public MessageResponseDTO remove(Long petId) {
    return removeUserFavoritePetGateway.remove(petId);
  }
}
