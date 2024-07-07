package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.AddUserFavoritePetGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.pet.AddUserFavoritePetUseCase;

public class AddUserFavoritePetUseCaseImpl implements
    AddUserFavoritePetUseCase {

  private final AddUserFavoritePetGateway addUserFavoritePetGateway;

  public AddUserFavoritePetUseCaseImpl(AddUserFavoritePetGateway addUserFavoritePetGateway) {
    this.addUserFavoritePetGateway = addUserFavoritePetGateway;
  }

  @Override
  public MessageResponseDTO add(Long petId) {
    return addUserFavoritePetGateway.add(petId);
  }
}
