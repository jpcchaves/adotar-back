package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.LoginRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;

public interface LoginGateway {

  LoginResponseDTO login(LoginRequestDTO requestDTO);
}
