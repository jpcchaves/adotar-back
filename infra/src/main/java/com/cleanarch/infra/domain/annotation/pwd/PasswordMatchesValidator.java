package com.cleanarch.infra.domain.annotation.pwd;

import com.cleanarch.infra.domain.dto.auth.RegisterRequestDTO;
import com.cleanarch.infra.domain.dto.auth.UpdatePasswordDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordMatchesValidator implements
    ConstraintValidator<PasswordMatches, Object> {

  @Override
  public boolean isValid(
      Object obj,
      ConstraintValidatorContext context
  ) {

    if (obj instanceof UpdatePasswordDTO updatePasswordDTO) {

      return Objects.equals(updatePasswordDTO.getNewPassword(),
          updatePasswordDTO.getConfirmNewPassword());

    }

    if (obj instanceof RegisterRequestDTO registerRequestDTO) {

      return Objects.equals(registerRequestDTO.getPassword(),
          registerRequestDTO.getConfirmPassword());
    }

    return false;
  }
}
