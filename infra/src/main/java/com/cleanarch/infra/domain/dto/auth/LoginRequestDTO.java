package com.cleanarch.infra.domain.dto.auth;

import com.cleanarch.usecase.auth.dto.BaseLoginRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO extends BaseLoginRequestDTO {

  @Override
  @Email(message = "Invalid email!")
  @NotBlank(message = "Email cannot be blank or null")
  public String getEmail() {
    return super.getEmail();
  }

  @Override
  @NotBlank(message = "Password cannot be blank or null")
  public String getPassword() {
    return super.getPassword();
  }
}
