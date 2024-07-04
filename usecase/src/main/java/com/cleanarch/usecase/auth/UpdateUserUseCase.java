package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

@FunctionalInterface
public interface UpdateUserUseCase {
  MessageResponseDTO updateUser(
      UpdateUserRequestDTO requestDTO);
}
