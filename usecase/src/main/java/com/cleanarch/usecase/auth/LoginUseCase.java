package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.LoginRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;

@FunctionalInterface
public interface LoginUseCase {

  LoginResponseDTO login(LoginRequestDTO requestDTO);
}
