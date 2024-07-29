package com.cleanarch.infra.service.user;

import com.cleanarch.infra.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User getUserByEmail(String email);
}
