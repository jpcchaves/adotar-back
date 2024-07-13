package com.cleanarch.infra.service.user;

import com.cleanarch.infra.domain.model.User;

public interface UserService {

  User getUserByEmail(String email);

}
