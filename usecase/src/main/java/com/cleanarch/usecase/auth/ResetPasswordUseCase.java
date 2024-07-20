package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.BasePasswordResetTokenRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface ResetPasswordUseCase {

  MessageResponseDTO resetPassword(BasePasswordResetTokenRequestDTO requestDTO);
}
