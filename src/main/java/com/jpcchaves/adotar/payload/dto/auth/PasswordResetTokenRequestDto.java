package com.jpcchaves.adotar.payload.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class PasswordResetTokenRequestDto {
    @NotBlank(message = "Informe um email para continuar")
    @Email(message = "Informe um email válido")
    private String email;

    @NotBlank(message = "A nova senha é obrigatória!")
    @Length(min = 6, message = "Senha deve conter pelo menos 6 caracteres!")
    private String newPassword;

    @NotBlank(message = "A confirmação de senha é obrigatória!")
    @Length(min = 6, message = "Senha deve conter pelo menos 6 caracteres!")
    private String confirmNewPassword;

    public PasswordResetTokenRequestDto() {
    }

    public PasswordResetTokenRequestDto(String email,
                                        String newPassword,
                                        String confirmNewPassword) {
        this.email = email;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
