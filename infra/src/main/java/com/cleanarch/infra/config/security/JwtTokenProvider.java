package com.cleanarch.infra.config.security;

import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {

  String generateToken(Authentication authentication);

  String getUsernameFromToken(String token);

  boolean validateToken(String token);

  String getClaimFromTokenByKey(
      String token,
      String key
  );
}
