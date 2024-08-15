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
import com.cleanarch.infra.service.mail.MailService;
import com.cleanarch.infra.service.mail.MailTemplates;
import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegisterGatewayImpl implements RegisterGateway {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserFactory userFactory;
  private final MailService mailService;
  private MailTemplates mailTemplates;

  public RegisterGatewayImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder,
      UserFactory userFactory,
      MailService mailService,
      MailTemplates mailTemplates) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.userFactory = userFactory;
    this.mailService = mailService;
    this.mailTemplates = mailTemplates;
  }

  @Override
  @Transactional
  public String register(BaseRegisterRequestDTO requestDTO) {

    if (userRepository.existsByEmail(requestDTO.getEmail())) {

      throw new BadRequestException(ExceptionDefinition.USR0002);
    }

    Role role =
        roleRepository
            .findByName(Roles.ROLE_USER)
            .orElseThrow(() -> new InternalServerError(ExceptionDefinition.INT0001));

    String encodedPassword = passwordEncoder.encode(requestDTO.getPassword());

    User user =
        userFactory.buildUser(
            requestDTO.getFirstName(),
            requestDTO.getLastName(),
            requestDTO.getEmail(),
            encodedPassword,
            role);

    User savedUser = userRepository.save(user);

    mailService.sendEmail(
        "Welcome to Adotar!",
        mailTemplates.getRegisterSuccessfulTemplate(
            savedUser.getFirstName() + " " + savedUser.getLastName()),
        savedUser.getEmail());

    return "Usuario cadastrado com sucesso!";
  }
}
