package com.cleanarch.infra.factory.auth;

import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.factory.user.UserFactory;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class ConcreteAuthFactory implements AuthFactory {

  private final UserFactory userFactory;

  public ConcreteAuthFactory(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  @Override
  public Authentication buildUsernamePasswordAuthToken(
      Object usernameOrEmail,
      Object password,
      Collection<? extends GrantedAuthority> authorities) {

    return new UsernamePasswordAuthenticationToken(
        usernameOrEmail, password, authorities);
  }

  @Override
  public LoginResponseDTO buildLoginReponse(String token, User user) {

    return new LoginResponseDTO(
        token,
        userFactory.buildUserMinDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail()));
  }
}
