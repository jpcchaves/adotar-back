package com.cleanarch.infra.gateway.auth;

import br.com.jpcchaves.core.domain.constants.Roles;
import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.InternalServerError;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.application.gateway.auth.RegisterGateway;
import com.cleanarch.infra.domain.model.Role;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.factory.user.UserFactory;
import com.cleanarch.infra.repository.RoleRepository;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class RegisterGatewayImpl implements RegisterGateway {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserFactory userFactory;

  public RegisterGatewayImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder,
      UserFactory userFactory
  ) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.userFactory = userFactory;
  }

  @Override
  @Transactional
  public String register(BaseRegisterRequestDTO requestDTO) {

    if (!Objects.equals(requestDTO.getPassword(),
                        requestDTO.getConfirmPassword())) {

      throw new BadRequestException(ExceptionDefinition.USR0003);
    }

    if (userRepository.existsByEmail(requestDTO.getEmail())) {

      throw new BadRequestException(ExceptionDefinition.USR0002);
    }

    Role role = roleRepository.findByName(Roles.ROLE_USER)
                              .orElseThrow(() -> new InternalServerError(
                                  ExceptionDefinition.INT0001));

    String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());

    User user = userFactory.buildUser(
        requestDTO.getFirstName(),
        requestDTO.getLastName(),
        requestDTO.getEmail(),
        encodedPassword,
        role
    );
    
    userRepository.save(user);

    return "Usuario cadastrado com sucesso!";
  }
}
