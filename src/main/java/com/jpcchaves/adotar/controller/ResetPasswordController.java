package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetTokenRequestDto;
import com.jpcchaves.adotar.service.usecases.PasswordResetService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forgot-password")
@CrossOrigin
public class ResetPasswordController {
    private final PasswordResetService passwordResetService;

    public ResetPasswordController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping
    public ResponseEntity<ApiMessageResponseDto> resetTokenRequestDto(@Valid @RequestBody PasswordResetRequestDto passwordResetRequestDto) throws MessagingException {
        return ResponseEntity.ok(passwordResetService.resetTokenRequestDto(passwordResetRequestDto));
    }

    @PostMapping("/redefine")
    public ResponseEntity<ApiMessageResponseDto> resetPassword(@Valid @RequestBody
                                                               PasswordResetTokenRequestDto passwordResetTokenRequestDto) {
        return ResponseEntity.ok(passwordResetService.resetPassword(passwordResetTokenRequestDto));
    }
}
