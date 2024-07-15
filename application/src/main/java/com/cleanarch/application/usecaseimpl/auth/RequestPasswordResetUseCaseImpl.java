package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.common.dto.*;

public class RequestPasswordResetUseCaseImpl
    implements RequestPasswordResetUseCase {

  private final RequestPasswordResetGateway requestPasswordResetGateway;

  public RequestPasswordResetUseCaseImpl(
      RequestPasswordResetGateway requestPasswordResetGateway
  ) {
    this.requestPasswordResetGateway = requestPasswordResetGateway;
  }

  @Override
  public MessageResponseDTO resetTokenRequest(
      BasePasswordResetRequestDTO requestDTO
  ) {

    return requestPasswordResetGateway.resetTokenRequest(requestDTO);
  }
}
