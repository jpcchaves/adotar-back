package br.com.jpcchaves.core.domain.model;

import java.time.Instant;

public class PasswordResetToken {

  private Long id;
  private String token;
  private Instant expirationTime;
  private User user;

  public PasswordResetToken() {}

  public PasswordResetToken(
      Long id, String token, Instant expirationTime, User user) {

    this.id = id;
    this.token = token;
    this.expirationTime = expirationTime;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Instant getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(Instant expirationTime) {
    this.expirationTime = expirationTime;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
