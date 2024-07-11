package com.cleanarch.infra.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtUtils {

  private static final String BEARER_PREFIX = "Bearer";
  private static final String BLANK_SPACE = " ";

  private final JwtTokenProvider jwtTokenProvider;

  public JwtUtils(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  private String getTokenFromRequest(HttpServletRequest request) {

    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (headerHasBearer(authorizationHeader)) {
      return authorizationHeader.substring(7);
    }

    return null;
  }

  private Boolean headerHasBearer(String authorizationHeader) {

    return StringUtils.hasText(authorizationHeader)
        && authorizationHeader.startsWith(BEARER_PREFIX + BLANK_SPACE);
  }

  private Boolean isTokenValid(String token) {

    return StringUtils.hasText(token) && jwtTokenProvider.validateToken(token);
  }

}
