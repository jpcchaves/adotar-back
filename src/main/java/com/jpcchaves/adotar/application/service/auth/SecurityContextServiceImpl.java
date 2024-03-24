package com.jpcchaves.adotar.application.service.auth;

import com.jpcchaves.adotar.application.service.auth.contracts.SecurityContextService;
import com.jpcchaves.adotar.domain.model.User;
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
