package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.RegisterGateway;
import com.cleanarch.usecase.auth.RegisterUseCase;
import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;

public class RegisterUseCaseImpl implements RegisterUseCase {

  private final RegisterGateway registerGateway;

  public RegisterUseCaseImpl(RegisterGateway registerGateway) {
    this.registerGateway = registerGateway;
  }

  @Override
  public String register(BaseRegisterRequestDTO requestDTO) {
    return registerGateway.register(requestDTO);
  }
}
