package com.cleanarch.infra.service.auth;

import com.cleanarch.infra.domain.dto.auth.LoginRequestDTO;
import com.cleanarch.infra.domain.dto.auth.RegisterRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public interface AuthService {

  MessageResponseDTO register(RegisterRequestDTO requestDTO);

  LoginResponseDTO login(LoginRequestDTO requestDTO);
}
