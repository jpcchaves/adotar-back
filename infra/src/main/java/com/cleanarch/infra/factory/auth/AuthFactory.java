package com.cleanarch.infra.factory.auth;

import com.cleanarch.infra.domain.model.User;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public interface AuthFactory {

  Authentication buildUsernamePasswordAuthToken(
      Object usernameOrEmail,
      Object password,
      Collection<? extends GrantedAuthority> authorities);

  LoginResponseDTO buildLoginReponse(String token, User user);
}
