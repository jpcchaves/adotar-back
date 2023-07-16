package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetTokenRequestDto;
import com.jpcchaves.adotar.service.usecases.PasswordResetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forgot-password")
@CrossOrigin
@Tag(name = "Reset Password-Controller")
public class ResetPasswordController {
    private final PasswordResetService passwordResetService;

    public ResetPasswordController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Operation(summary = "Requests a password reset",
            description = "To initiate a password reset, please provide the user's email address. An email will then be sent to the user's registered email address, containing a token that can be used to reset their password",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiMessageResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping
    public ResponseEntity<ApiMessageResponseDto> resetTokenRequestDto(@Valid @RequestBody PasswordResetRequestDto passwordResetRequestDto) throws MessagingException {
        return ResponseEntity.ok(passwordResetService.resetTokenRequestDto(passwordResetRequestDto));
    }

    @Operation(summary = "Redefine user's password",
            description = "Redefine user's password by passing  the code that was sent to their email, as well as the new password and its confirmation",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiMessageResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping("/redefine")
    public ResponseEntity<ApiMessageResponseDto> resetPassword(@Valid @RequestBody
                                                               PasswordResetTokenRequestDto passwordResetTokenRequestDto) {
        return ResponseEntity.ok(passwordResetService.resetPassword(passwordResetTokenRequestDto));
    }
}
