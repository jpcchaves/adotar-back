package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.common.dto.*;

public class UpdatePasswordUseCaseImpl implements UpdatePasswordUseCase {

  private final UpdatePasswordGateway updatePasswordGateway;

  public UpdatePasswordUseCaseImpl(
      UpdatePasswordGateway updatePasswordGateway) {
    this.updatePasswordGateway = updatePasswordGateway;
  }

  @Override
  public MessageResponseDTO updatePassword(
      UpdatePasswordRequestDTO requestDTO) {

    return updatePasswordGateway.updatePassword(requestDTO);
  }
}
