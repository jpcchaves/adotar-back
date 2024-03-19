package com.jpcchaves.adotar.application.service.usecases.v1;

import com.jpcchaves.adotar.domain.model.User;

public interface SecurityContextService {
  User getCurrentLoggedUser();
}
