package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;

public class LoginUseCaseImpl implements LoginUseCase {

  private final LoginGateway loginGateway;

  public LoginUseCaseImpl(LoginGateway loginGateway) {
    this.loginGateway = loginGateway;
  }

  @Override
  public LoginResponseDTO login(BaseLoginRequestDTO requestDTO) {
    return loginGateway.login(requestDTO);
  }
}
