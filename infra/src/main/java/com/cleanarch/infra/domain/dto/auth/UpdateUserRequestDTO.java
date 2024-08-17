package com.cleanarch.infra.domain.dto.auth;

import com.cleanarch.usecase.auth.dto.BaseUpdateUserRequestDTO;
import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequestDTO extends BaseUpdateUserRequestDTO {

  public UpdateUserRequestDTO(
      String firstName, String lastName, String phone, String phone2) {
    super(firstName, lastName, phone, phone2);
  }

  @Override
  @NotBlank(message = "Field firstName is required")
  public String getFirstName() {
    return super.getFirstName();
  }

  @Override
  @NotBlank(message = "Field lastName is required")
  public String getLastName() {
    return super.getLastName();
  }

  @Override
  @NotBlank(message = "Field phone is required")
  public String getPhone() {
    return super.getPhone();
  }

  @Override
  @NotBlank(message = "Field phone2 is required")
  public String getPhone2() {
    return super.getPhone2();
  }
}
