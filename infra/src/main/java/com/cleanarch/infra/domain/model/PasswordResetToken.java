package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import java.io.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "password_reset_token")
@SequenceGenerator(
    name = "seq_pass_reset_token",
    sequenceName = "seq_pass_reset_token",
    allocationSize = 1)
public class PasswordResetToken implements Serializable {

  @Serial private static final long serialVersionUID = -7667069758586804655L;

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "seq_pass_reset_token")
  private Long id;

  @Column(length = 6, nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant expirationTime;

  @OneToOne(
      fetch = FetchType.LAZY,
      optional = false,
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      targetEntity = User.class)
  @JoinColumn(
      name = "user_id",
      nullable = false,
      unique = true,
      referencedColumnName = "id",
      foreignKey =
          @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT))
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PasswordResetToken that = (PasswordResetToken) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "PasswordResetToken{"
        + "id="
        + id
        + ", token='"
        + token
        + '\''
        + ", expirationTime="
        + expirationTime
        + ", user="
        + user
        + '}';
  }
}
