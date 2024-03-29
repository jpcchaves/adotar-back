package com.jpcchaves.adotar.application.service.mail.contracts;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import jakarta.mail.MessagingException;

public interface EmailService {
  void sendResetPasswordRequest(PasswordResetToken passwordResetRequestDto)
      throws MessagingException;

  ApiMessageResponseDto sendContactMessage(ContactEmailDto contactEmailDto)
      throws MessagingException;
}
