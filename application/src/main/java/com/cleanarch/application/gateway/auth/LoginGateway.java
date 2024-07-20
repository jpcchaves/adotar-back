package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.BaseLoginRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;

@FunctionalInterface
public interface LoginGateway {

  LoginResponseDTO login(BaseLoginRequestDTO requestDTO);
}
