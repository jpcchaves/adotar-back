package com.cleanarch.usecase.auth.dto;

public class BasePasswordResetRequestDTO {
  private String email;

  public BasePasswordResetRequestDTO() {}

  public BasePasswordResetRequestDTO(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
