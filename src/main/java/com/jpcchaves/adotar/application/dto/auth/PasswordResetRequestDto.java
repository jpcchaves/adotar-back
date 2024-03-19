package com.jpcchaves.adotar.application.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PasswordResetRequestDto {
  @NotBlank(message = "Informe um email para continuar")
  @Email(message = "Informe um email válido")
  private String email;

  public PasswordResetRequestDto() {}

  public PasswordResetRequestDto(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
