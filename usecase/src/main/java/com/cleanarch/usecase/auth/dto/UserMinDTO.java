package com.cleanarch.usecase.auth.dto;

public class UserMinDTO {

  private Long id;
  private String fullName;
  private String email;

  public UserMinDTO() {
  }

  public UserMinDTO(
      Long id,
      String fullName,
      String email
  ) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
