package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.BasePasswordResetRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface RequestPasswordResetUseCase {

  MessageResponseDTO resetTokenRequest(BasePasswordResetRequestDTO requestDTO);
}
