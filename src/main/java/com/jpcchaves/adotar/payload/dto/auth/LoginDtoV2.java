package com.jpcchaves.adotar.payload.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LoginDtoV2 {
    @NotBlank(message = "O email é obrigatório para realizar o login!")
    @Email(message = "Informe um email válido!")
    private String email;

    @NotBlank(message = "A senha é obrigatória para realizar o login!")
    @Length(min = 6, message = "Senha deve conter pelo menos 6 caracteres!")
    private String password;

    public LoginDtoV2() {
    }

    public LoginDtoV2(
            String email,
            String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
