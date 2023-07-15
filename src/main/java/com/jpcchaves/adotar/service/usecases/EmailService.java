package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.domain.entities.PasswordResetToken;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendPasswordRequest(PasswordResetToken passwordResetRequestDto) throws MessagingException;
}
