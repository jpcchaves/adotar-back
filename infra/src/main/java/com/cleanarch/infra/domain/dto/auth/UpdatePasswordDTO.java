package com.cleanarch.infra.domain.dto.auth;

import com.cleanarch.usecase.auth.dto.BaseUpdatePasswordRequestDTO;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UpdatePasswordDTO extends BaseUpdatePasswordRequestDTO {

  @Override
  @NotBlank(message = "The current password is required!")
  @Length(min = 8, message = "Password must have at least 8 characters")
  public String getCurrentPassword() {
    return super.getCurrentPassword();
  }

  @Override
  @NotBlank(message = "The new password is required!")
  @Length(min = 8, message = "Password must have at least 8 characters")
  public String getNewPassword() {
    return super.getNewPassword();
  }

  @Override
  @NotBlank(message = "The confirm new password is required!")
  @Length(min = 8, message = "Password must have at least 8 characters")
  public String getConfirmNewPassword() {
    return super.getConfirmNewPassword();
  }
}
