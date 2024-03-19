package com.jpcchaves.adotar.application.dto.auth;

public class RegisterResponseDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private Boolean isAdmin;

  public RegisterResponseDto() {}

  public RegisterResponseDto(
      Long id,
      String firstName,
      String lastName,
      String username,
      String email,
      Boolean isAdmin) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.isAdmin = isAdmin;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }
}
