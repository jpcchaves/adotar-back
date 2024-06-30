package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.LoginRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;

public interface LoginUseCase {

  LoginResponseDTO login(LoginRequestDTO requestDTO);
}
