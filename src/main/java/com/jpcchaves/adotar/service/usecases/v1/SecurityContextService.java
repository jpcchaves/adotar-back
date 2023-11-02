package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.domain.entities.User;

public interface SecurityContextService {
    User getCurrentLoggedUser();
}
