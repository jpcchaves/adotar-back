package com.cleanarch.infra.factory.user;

import com.cleanarch.infra.domain.model.Role;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.usecase.auth.dto.UserMinDTO;
import org.springframework.stereotype.Component;

@Component
public class ConcreteUserFactory implements UserFactory {

  @Override
  public UserMinDTO buildUserMinDTO(
      Long id, String firstName, String lastName, String email) {

    return new UserMinDTO(id, firstName + " " + lastName, email);
  }

  @Override
  public User buildUser(
      String firstName,
      String lastName,
      String email,
      String encodedPassword,
      Role role) {

    User user = new User();

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(encodedPassword);
    user.getRoles().add(role);

    return user;
  }
}
