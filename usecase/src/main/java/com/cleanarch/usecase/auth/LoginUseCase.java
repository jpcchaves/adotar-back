package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.*;

@FunctionalInterface
public interface LoginUseCase {

  LoginResponseDTO login(BaseLoginRequestDTO requestDTO);
}
