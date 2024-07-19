package com.cleanarch.infra.service.auth.impl;

import com.cleanarch.infra.domain.dto.auth.LoginRequestDTO;
import com.cleanarch.infra.domain.dto.auth.RegisterRequestDTO;
import com.cleanarch.infra.factory.common.ConcreteMessageResponseFactory;
import com.cleanarch.infra.service.auth.AuthService;
import com.cleanarch.usecase.auth.LoginUseCase;
import com.cleanarch.usecase.auth.RegisterUseCase;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final RegisterUseCase registerUseCase;
  private final LoginUseCase loginUseCase;

  public AuthServiceImpl(
      RegisterUseCase registerUseCase,
      LoginUseCase loginUseCase
  ) {
    this.registerUseCase = registerUseCase;
    this.loginUseCase = loginUseCase;
  }

  @Override
  public MessageResponseDTO register(RegisterRequestDTO requestDTO) {
    return ConcreteMessageResponseFactory.buildMessage(
        registerUseCase.register(requestDTO));
  }

  @Override
  public LoginResponseDTO login(LoginRequestDTO requestDTO) {
    return loginUseCase.login(requestDTO);
  }
}
