package com.cleanarch.usecase.auth.dto;

public class UpdateUserRequestDTO {

  private String firstName;
  private String lastName;
  private String currentPassword;
  private String password;

  public UpdateUserRequestDTO(
      String firstName,
      String lastName,
      String currentPassword,
      String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.currentPassword = currentPassword;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
