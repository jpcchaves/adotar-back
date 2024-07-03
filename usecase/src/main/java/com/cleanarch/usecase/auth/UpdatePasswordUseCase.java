package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

@FunctionalInterface
public interface UpdatePasswordUseCase {

  MessageResponseDTO updatePassword(
      UpdatePasswordRequestDTO requestDTO);
}
