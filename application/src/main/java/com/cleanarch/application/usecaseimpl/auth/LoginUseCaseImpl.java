package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.LoginGateway;
import com.cleanarch.usecase.auth.LoginUseCase;
import com.cleanarch.usecase.auth.dto.LoginRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;

public class LoginUseCaseImpl implements LoginUseCase {

  private final LoginGateway loginGateway;

  public LoginUseCaseImpl(LoginGateway loginGateway) {
    this.loginGateway = loginGateway;
  }

  @Override
  public LoginResponseDTO login(LoginRequestDTO requestDTO) {
    return loginGateway.login(requestDTO);
  }
}
