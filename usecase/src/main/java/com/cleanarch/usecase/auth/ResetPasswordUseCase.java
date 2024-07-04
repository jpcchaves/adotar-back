package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

public interface ResetPasswordUseCase {

  MessageResponseDTO resetPassword(
      PasswordResetTokenRequestDTO requestDTO
  );
}
