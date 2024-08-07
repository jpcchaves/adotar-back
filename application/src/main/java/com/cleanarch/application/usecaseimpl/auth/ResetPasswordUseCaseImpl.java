package com.cleanarch.application.usecaseimpl.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.usecase.auth.*;
import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.common.dto.*;

public class ResetPasswordUseCaseImpl implements ResetPasswordUseCase {

  private final ResetPasswordGateway resetPasswordGateway;

  public ResetPasswordUseCaseImpl(ResetPasswordGateway resetPasswordGateway) {
    this.resetPasswordGateway = resetPasswordGateway;
  }

  @Override
  public MessageResponseDTO resetPassword(
      BasePasswordResetTokenRequestDTO requestDTO
  ) {

    return resetPasswordGateway.resetPassword(requestDTO);
  }
}
