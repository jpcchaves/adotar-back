package com.cleanarch.infra.factory.user;

import com.cleanarch.usecase.auth.dto.UserMinDTO;
import org.springframework.stereotype.Component;

@Component
public class ConcreteUserFactory implements UserFactory {
  
  @Override
  public UserMinDTO buildUserMinDTO(
      Long id,
      String firstName,
      String lastName,
      String email
  ) {

    return new UserMinDTO(id, firstName + " " + lastName, email);
  }
}
