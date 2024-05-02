package com.jpcchaves.adotar.infrastructure.application.service.auth;

import com.jpcchaves.adotar.infrastructure.application.service.auth.contracts.SecurityContextService;
import com.jpcchaves.adotar.infrastructure.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {
  @Override
  public User getCurrentLoggedUser() {
    return (User)
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
