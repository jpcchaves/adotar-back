package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;

@FunctionalInterface
public interface RegisterUseCase {

  String register(BaseRegisterRequestDTO requestDTO);
}
