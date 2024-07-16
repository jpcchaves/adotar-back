package com.cleanarch.infra.gateway.auth;

import br.com.jpcchaves.core.domain.constants.Roles;
import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.InternalServerError;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.application.gateway.auth.RegisterGateway;
import com.cleanarch.infra.domain.model.Role;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.repository.RoleRepository;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegisterGatewayImpl implements RegisterGateway {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public RegisterGatewayImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
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

    User user = new User();

    user.setFirstName(requestDTO.getFirstName());
    user.setLastName(requestDTO.getLastName());
    user.setEmail(requestDTO.getEmail());
    user.setPassword(
        passwordEncoder.encode(requestDTO.getPassword())
    );
    user.getRoles().add(role);

    userRepository.save(user);

    return "Usuario cadastrado com sucesso!";
  }
}
