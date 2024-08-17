package com.cleanarch.usecase.auth.dto;

public class BaseUpdateUserRequestDTO {

  private String firstName;
  private String lastName;
  private String phone;
  private String phone2;

  public BaseUpdateUserRequestDTO(
      String firstName, String lastName, String phone, String phone2) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.phone2 = phone2;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone2() {
    return phone2;
  }

  public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }
}
