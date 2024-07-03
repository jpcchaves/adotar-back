package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

@FunctionalInterface
public interface ResetPasswordGateway {

  MessageResponseDTO resetPassword(
      PasswordResetTokenRequestDTO requestDTO
  );
}
