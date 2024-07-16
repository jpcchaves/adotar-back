package com.cleanarch.infra.factory.user;

import com.cleanarch.usecase.auth.dto.UserMinDTO;

public interface UserFactory {

  UserMinDTO buildUserMinDTO(
      Long id,
      String firstName,
      String lastName,
      String email
  );
}
