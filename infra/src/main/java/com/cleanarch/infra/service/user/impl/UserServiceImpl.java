package com.cleanarch.infra.service.user.impl;

import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.infra.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(
            () -> new BadRequestException(ExceptionDefinition.USR0001));
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    return getUserByEmail(username);
  }
}
