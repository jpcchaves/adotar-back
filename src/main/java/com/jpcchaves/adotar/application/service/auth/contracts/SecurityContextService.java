package com.jpcchaves.adotar.application.service.auth.contracts;

import com.jpcchaves.adotar.domain.model.User;

public interface SecurityContextService {
  User getCurrentLoggedUser();
}
