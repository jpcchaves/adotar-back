package com.jpcchaves.adotar.application.service.usecases;

import com.jpcchaves.adotar.domain.model.User;

public interface SecurityContextService {
  User getCurrentLoggedUser();
}
