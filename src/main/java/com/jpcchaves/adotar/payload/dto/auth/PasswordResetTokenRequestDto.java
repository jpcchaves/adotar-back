package com.jpcchaves.adotar.payload.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class PasswordResetTokenRequestDto {
    @NotBlank(message = "O token é obrigatório")
    @Length(min = 6, max = 6, message = "O token deve conter 6 caracteres")
    private String token;

    @NotBlank(message = "A nova senha é obrigatória!")
    @Length(min = 6, message = "Senha deve conter pelo menos 6 caracteres!")
    private String newPassword;

    @NotBlank(message = "A confirmação de senha é obrigatória!")
    @Length(min = 6, message = "Senha deve conter pelo menos 6 caracteres!")
    private String confirmNewPassword;

    public PasswordResetTokenRequestDto() {
    }

    public PasswordResetTokenRequestDto(String token,
                                        String newPassword,
                                        String confirmNewPassword) {
        this.token = token;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
