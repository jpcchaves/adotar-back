package com.cleanarch.infra.config.security.constants;

public class RequestMatchersConstants {

  public static final String[] PUBLIC_REQUEST_MATCHERS =
      new String[]{
          "/h2/**",
          "/h2-console/**",
          "/api/v1/auth/login"
      };

}
