package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.RegisterRequestDTO;

public interface RegisterUseCase {

  String register(RegisterRequestDTO requestDTO);
}
