package com.cleanarch.infra.factory.auth;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class ConcreteAuthFactory implements AuthFactory {

  @Override
  public Authentication buildUsernamePasswordAuthToken(
      Object usernameOrEmail,
      Object password,
      Collection<? extends GrantedAuthority> authorities
  ) {

    return new UsernamePasswordAuthenticationToken(usernameOrEmail, password,
        authorities);
  }
}
