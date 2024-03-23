package com.jpcchaves.adotar.application.service.usecases;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.application.dto.auth.PasswordResetTokenRequestDto;
import jakarta.mail.MessagingException;

public interface PasswordResetService {
  ApiMessageResponseDto resetTokenRequestDto(
      PasswordResetRequestDto passwordResetRequestDto)
      throws MessagingException;

  ApiMessageResponseDto resetPassword(
      PasswordResetTokenRequestDto passwordResetTokenRequestDto);
}
