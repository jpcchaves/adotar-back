package com.jpcchaves.adotar.application.service.usecases.v1;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.application.dto.email.ContactEmailDto;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendResetPasswordRequest(PasswordResetToken passwordResetRequestDto) throws MessagingException;

    ApiMessageResponseDto sendContactMessage(ContactEmailDto contactEmailDto) throws MessagingException;
}
