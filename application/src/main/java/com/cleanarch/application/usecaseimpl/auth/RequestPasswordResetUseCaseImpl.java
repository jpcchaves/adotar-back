package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

public class RequestPasswordResetUseCaseImpl implements RequestPasswordResetUseCase {

  private final RequestPasswordResetGateway requestPasswordResetGateway;

  public RequestPasswordResetUseCaseImpl(RequestPasswordResetGateway requestPasswordResetGateway) {
    this.requestPasswordResetGateway = requestPasswordResetGateway;
  }

  @Override
  public MessageResponseDTO resetTokenRequest(PasswordResetRequestDTO requestDTO) {

    return requestPasswordResetGateway.resetTokenRequest(requestDTO);
  }
}
