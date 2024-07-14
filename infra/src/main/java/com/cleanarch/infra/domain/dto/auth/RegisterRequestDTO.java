package com.cleanarch.infra.domain.dto.auth;

import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class RegisterRequestDTO extends BaseRegisterRequestDTO {

  @Override
  @NotBlank(message = "O nome e obrigatorio!")
  public String getFirstName() {
    return super.getFirstName();
  }

  @Override
  @NotBlank(message = "O email e obrigatorio!")
  @Email(message = "Informe um email valido!")
  public String getEmail() {
    return super.getEmail();
  }

  @Override
  @NotBlank(message = "A senha e obrigatoria!")
  @Length(min = 8, message = "A senha deve conter 8 caracteres ou mais")
  public String getPassword() {
    return super.getPassword();
  }
  
  @Override
  @NotBlank(message = "A confirmacao de senha e obrigatoria!")
  @Length(min = 8, message = "A senha deve conter 8 caracteres ou mais")
  public String getConfirmPassword() {
    return super.getConfirmPassword();
  }
}
