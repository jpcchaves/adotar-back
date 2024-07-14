package br.com.jpcchaves.core.domain.constants;

public abstract class Roles {

  public static final String ROLE_USER = "ROLE_USER";

  public static final String ROLE_ADMIN = "ROLE_ADMIN";

  public static String getDefault() {
    return ROLE_USER;
  }
}
