package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

public class ResetPasswordUseCaseImpl implements ResetPasswordUseCase {

  private final ResetPasswordGateway resetPasswordGateway;

  public ResetPasswordUseCaseImpl(ResetPasswordGateway resetPasswordGateway) {
    this.resetPasswordGateway = resetPasswordGateway;
  }

  @Override
  public MessageResponseDTO resetPassword(PasswordResetTokenRequestDTO requestDTO) {

    return resetPasswordGateway.resetPassword(requestDTO);
  }
}
