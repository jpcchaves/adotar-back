package com.jpcchaves.adotar.infrastructure.application.service.pwdreset.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.PasswordResetTokenRequestDto;
import jakarta.mail.MessagingException;

public interface PasswordResetService {
  ApiMessageResponseDto resetTokenRequestDto(
      PasswordResetRequestDto passwordResetRequestDto)
      throws MessagingException;

  ApiMessageResponseDto resetPassword(
      PasswordResetTokenRequestDto passwordResetTokenRequestDto);
}
