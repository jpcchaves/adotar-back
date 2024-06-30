package com.cleanarch.usecase.auth.dto;

public class LoginResponseDTO {

  private String accessToken;
  private UserMinDTO user;

  public LoginResponseDTO() {
  }

  public LoginResponseDTO(
      String accessToken,
      UserMinDTO user
  ) {
    this.accessToken = accessToken;
    this.user = user;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public UserMinDTO getUser() {
    return user;
  }

  public void setUser(UserMinDTO user) {
    this.user = user;
  }
}
