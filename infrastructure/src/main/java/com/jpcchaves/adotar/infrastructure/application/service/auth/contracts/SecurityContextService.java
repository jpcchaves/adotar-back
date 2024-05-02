package com.jpcchaves.adotar.infrastructure.application.service.auth.contracts;

import com.jpcchaves.adotar.infrastructure.domain.model.User;

public interface SecurityContextService {
  User getCurrentLoggedUser();
}
