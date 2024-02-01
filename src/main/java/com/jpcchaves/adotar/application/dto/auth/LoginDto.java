package com.jpcchaves.adotar.application.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LoginDto {
    @NotBlank(message = "O usuário ou email são obrigatórios para rezalizar o login!")
    private String usernameOrEmail;

    @NotBlank(message = "A senha é obrigatória para realizar o login!")
    @Length(min = 6, message = "Senha deve conter pelo menos 6 caracteres!")
    private String password;

    public LoginDto() {
    }

    public LoginDto(String usernameOrEmail,
                    String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
