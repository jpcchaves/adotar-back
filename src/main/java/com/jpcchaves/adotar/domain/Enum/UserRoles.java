package com.jpcchaves.adotar.domain.Enum;

public enum UserRoles {
  ROLE_USER("ROLE_USER"),
  ROLE_ADMIN("ROLE_ADMIN");

  private final String role;

  UserRoles(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
