package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {
    @Override
    public User getCurrentLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
