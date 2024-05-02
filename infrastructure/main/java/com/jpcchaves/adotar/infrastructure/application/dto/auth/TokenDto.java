package com.jpcchaves.adotar.infrastructure.application.dto.auth;

public class TokenDto {
  private String accessToken;

  public TokenDto() {}

  public TokenDto(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
