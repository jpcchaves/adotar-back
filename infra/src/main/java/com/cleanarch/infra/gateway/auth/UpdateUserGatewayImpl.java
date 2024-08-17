package com.cleanarch.infra.gateway.auth;

import br.com.jpcchaves.core.exception.InternalServerError;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.application.gateway.auth.UpdateUserGateway;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.factory.common.ConcreteMessageResponseFactory;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.usecase.auth.dto.BaseUpdateUserRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserGatewayImpl implements UpdateUserGateway {

  private final UserRepository userRepository;
  private final SecurityContext securityContext;

  public UpdateUserGatewayImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.securityContext = SecurityContextHolder.getContext();
  }

  @Override
  public MessageResponseDTO updateUser(BaseUpdateUserRequestDTO requestDTO) {
    User user = (User) securityContext.getAuthentication().getPrincipal();

    user =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () -> new InternalServerError(ExceptionDefinition.INT0001));

    user.setFirstName(requestDTO.getFirstName());
    user.setLastName(requestDTO.getLastName());
    user.setPhone(requestDTO.getPhone());
    user.setPhone2(requestDTO.getPhone2());

    userRepository.saveAndFlush(user);

    return ConcreteMessageResponseFactory.buildMessage(
        "User updated successfully!");
  }
}
