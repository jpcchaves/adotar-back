package com.jpcchaves.adotar.infrastructure.application.service.user;

import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.infra.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail)
      throws UsernameNotFoundException {
    return userRepository
        .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Usuário não encontrado com os dados informados!"));
  }
}
