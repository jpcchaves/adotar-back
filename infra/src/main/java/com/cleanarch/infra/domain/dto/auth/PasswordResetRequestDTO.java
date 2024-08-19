package com.cleanarch.infra.domain.dto.auth;

import com.cleanarch.usecase.auth.dto.BasePasswordResetRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PasswordResetRequestDTO extends BasePasswordResetRequestDTO {

    public PasswordResetRequestDTO(String email) {

        super(email);
    }

    @Override
    @NotBlank(message = "Email is a required field!")
    @Email(message = "Invalid email!")
    public String getEmail() {

        return super.getEmail();
    }
}
