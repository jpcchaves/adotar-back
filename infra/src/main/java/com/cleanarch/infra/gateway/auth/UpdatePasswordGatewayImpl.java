package com.cleanarch.infra.gateway.auth;

import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.InternalServerError;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.application.gateway.auth.UpdatePasswordGateway;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.factory.common.ConcreteMessageResponseFactory;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.usecase.auth.dto.BaseUpdatePasswordRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordGatewayImpl implements UpdatePasswordGateway {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UpdatePasswordGatewayImpl(
      UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public MessageResponseDTO updatePassword(
      BaseUpdatePasswordRequestDTO requestDTO) {

    User userLogged =
        (User)
            SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

    User user =
        userRepository
            .findById(userLogged.getId())
            .orElseThrow(
                () -> new InternalServerError(ExceptionDefinition.INT0001));

    String encodedCurrentPassword = user.getPassword();

    if (!passwordEncoder.matches(
        requestDTO.getCurrentPassword(), encodedCurrentPassword)) {

      throw new BadRequestException(ExceptionDefinition.USR0004);
    }

    String encodedNewPassword =
        passwordEncoder.encode(requestDTO.getNewPassword());

    user.setPassword(encodedNewPassword);

    userRepository.save(user);

    return ConcreteMessageResponseFactory.buildMessage(
        "Password updated successfully!");
  }
}
