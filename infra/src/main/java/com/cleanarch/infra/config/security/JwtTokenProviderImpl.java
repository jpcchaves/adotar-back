package com.cleanarch.infra.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

  @Override
  public String generateToken(Authentication authentication) {
    return null;
  }

  @Override
  public String getUsernameFromToken(String token) {
    return null;
  }

  @Override
  public boolean validateToken(String token) {
    return false;
  }

  @Override
  public String getClaimFromTokenByKey(
      String token,
      String key
  ) {
    return null;
  }
}
