package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.LoginRequestDTO;

public interface LoginUseCase {

  String login(LoginRequestDTO requestDTO);
}
