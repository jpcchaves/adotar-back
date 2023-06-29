package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.domain.entities.User;

public interface SecurityContextService {
    User getCurrentLoggedUser();
}
