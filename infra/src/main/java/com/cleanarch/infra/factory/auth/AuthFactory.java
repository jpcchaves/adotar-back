package com.cleanarch.infra.factory.auth;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public interface AuthFactory {

  Authentication buildUsernamePasswordAuthToken(
      Object usernameOrEmail,
      Object password,
      Collection<? extends GrantedAuthority> authorities
  );
}
