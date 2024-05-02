package com.jpcchaves.adotar.infrastructure.application.service.mail.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.infrastructure.domain.model.PasswordResetToken;
import jakarta.mail.MessagingException;

public interface EmailService {
  void sendResetPasswordRequest(PasswordResetToken passwordResetRequestDto)
      throws MessagingException;

  ApiMessageResponseDto sendContactMessage(ContactEmailDto contactEmailDto)
      throws MessagingException;
}
