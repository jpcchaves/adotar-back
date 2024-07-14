package com.cleanarch.infra.service.auth.impl;

import com.cleanarch.infra.domain.dto.auth.RegisterRequestDTO;
import com.cleanarch.infra.service.auth.AuthService;
import com.cleanarch.usecase.auth.RegisterUseCase;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final RegisterUseCase registerUseCase;

  public AuthServiceImpl(RegisterUseCase registerUseCase) {
    this.registerUseCase = registerUseCase;
  }

  @Override
  public MessageResponseDTO register(RegisterRequestDTO requestDTO) {
    return new MessageResponseDTO(registerUseCase.register(requestDTO));
  }
}
