package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetTokenRequestDto;
import jakarta.mail.MessagingException;

public interface PasswordResetService {
    ApiMessageResponseDto resetTokenRequestDto(PasswordResetRequestDto passwordResetRequestDto) throws MessagingException;

    ApiMessageResponseDto resetPassword(PasswordResetTokenRequestDto passwordResetTokenRequestDto);
}
