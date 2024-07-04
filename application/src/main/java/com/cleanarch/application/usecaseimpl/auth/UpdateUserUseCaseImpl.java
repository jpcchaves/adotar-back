package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

  private final UpdateUserGateway updateUserGateway;

  public UpdateUserUseCaseImpl(UpdateUserGateway updateUserGateway) {
    this.updateUserGateway = updateUserGateway;
  }

  @Override
  public MessageResponseDTO updateUser(UpdateUserRequestDTO requestDTO) {

    return updateUserGateway.updateUser(requestDTO);
  }
}
