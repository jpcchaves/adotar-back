package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.*;

@FunctionalInterface
public interface UpdateUserUseCase {
  String updateUser(
      UpdateUserRequestDTO requestDTO);
}
