package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.UnexpectedErrorException;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.service.usecases.UserService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final SecurityContextService securityContextService;
    private final MapperUtils mapperUtils;
    private final UserRepository userRepository;

    public UserServiceImpl(SecurityContextService securityContextService,
                           MapperUtils mapperUtils,
                           UserRepository userRepository) {
        this.securityContextService = securityContextService;
        this.mapperUtils = mapperUtils;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDto getUserDetails() {
        User user = userRepository
                .findById(securityContextService.getCurrentLoggedUser().getId())
                .orElseThrow(() -> new UnexpectedErrorException("Ocorreu um erro inesperado, tente novamente"));

        return buildUserDetails(user);
    }

    private UserDetailsDto buildUserDetails(User user) {
        return new UserDetailsDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                mapperUtils.parseObject(user.getContact(), ContactDto.class),
                mapperUtils.parseObject(user.getAddress(), AddressDto.class),
                user.getLastSeen(),
                user.getUpdatedAt(),
                user.getCreatedAt()
        );
    }
}
