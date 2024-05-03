package br.com.jpcchaves.core.domain.enums;

public enum UserRole {
  USER_ROLE("USER_ROLE"),
  ADMIN_ROLE("ADMIN_ROLE");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

  public static String getRole(String role) {
    for (UserRole userRole : values()) {
      if (userRole.getRole().equals(role)) {
        return userRole.getRole();
      }
    }

    throw new IllegalArgumentException("Invalid role: " + role);
  }

  public static boolean isRoleValid(String role) {
    return getRole(role).isBlank();
  }

  public String getRole() {
    return role;
  }
}
