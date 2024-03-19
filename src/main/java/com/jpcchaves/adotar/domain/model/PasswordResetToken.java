package com.jpcchaves.adotar.domain.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 6, nullable = false)
  private String token;

  private Instant expirationTime;

  @OneToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
  private User user;

  public PasswordResetToken() {}

  public PasswordResetToken(String token, Instant expirationTime, User user) {
    this.token = token;
    this.expirationTime = expirationTime;
    this.user = user;
  }

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
