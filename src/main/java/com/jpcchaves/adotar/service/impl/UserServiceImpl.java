package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.UnexpectedErrorException;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.service.usecases.UserService;
import com.jpcchaves.adotar.utils.user.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final SecurityContextService securityContextService;
    private final UserUtils userUtils;
    private final UserRepository userRepository;

    public UserServiceImpl(SecurityContextService securityContextService,
                           UserUtils userUtils,
                           UserRepository userRepository) {
        this.securityContextService = securityContextService;
        this.userUtils = userUtils;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDto getUserDetails() {
        User user = userRepository
                .findById(securityContextService.getCurrentLoggedUser().getId())
                .orElseThrow(() -> new UnexpectedErrorException("Ocorreu um erro inesperado, tente novamente"));

        return userUtils.buildUserDetails(user);
    }


}
