package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.domain.entities.PasswordResetToken;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.email.ContactEmailDto;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendResetPasswordRequest(PasswordResetToken passwordResetRequestDto) throws MessagingException;

    ApiMessageResponseDto sendContactMessage(ContactEmailDto contactEmailDto) throws MessagingException;
}
