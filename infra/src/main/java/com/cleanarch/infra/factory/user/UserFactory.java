package com.cleanarch.infra.factory.user;

import com.cleanarch.infra.domain.model.Role;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.usecase.auth.dto.UserMinDTO;

public interface UserFactory {

  UserMinDTO buildUserMinDTO(
      Long id, String firstName, String lastName, String email);

  User buildUser(
      String firstName,
      String lastName,
      String email,
      String encodedPassword,
      Role role);
}
