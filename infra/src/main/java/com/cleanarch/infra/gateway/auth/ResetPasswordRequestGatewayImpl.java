package com.cleanarch.infra.gateway.auth;

import br.com.jpcchaves.core.exception.ResourceNotFoundException;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.application.gateway.auth.RequestPasswordResetGateway;
import com.cleanarch.infra.domain.model.PasswordResetToken;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.factory.common.ConcreteMessageResponseFactory;
import com.cleanarch.infra.repository.PasswordResetTokenRepository;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.infra.service.mail.MailService;
import com.cleanarch.infra.service.mail.MailTemplates;
import com.cleanarch.infra.util.PasswordResetTokenUtils;
import com.cleanarch.usecase.auth.dto.BasePasswordResetRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ResetPasswordRequestGatewayImpl implements RequestPasswordResetGateway {

  private final UserRepository userRepository;
  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final MailService mailService;
  private final MailTemplates mailTemplates;

  public ResetPasswordRequestGatewayImpl(
      UserRepository userRepository,
      PasswordResetTokenRepository passwordResetTokenRepository,
      MailService mailService,
      MailTemplates mailTemplates) {
    this.userRepository = userRepository;
    this.passwordResetTokenRepository = passwordResetTokenRepository;
    this.mailService = mailService;
    this.mailTemplates = mailTemplates;
  }

  /*

  TODO (18/08/2024):
   - Create ResetPasswordRequest service method and Controller endpoint. Also, add integration test

  */

  @Override
  public MessageResponseDTO resetTokenRequest(BasePasswordResetRequestDTO requestDTO) {

    User user =
        userRepository
            .findByEmail(requestDTO.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionDefinition.USR0001));

    Optional<PasswordResetToken> optionalToken =
        passwordResetTokenRepository.findByUser(user.getId());

    PasswordResetToken token;

    if (optionalToken.isPresent()) {

      token = optionalToken.get();

      if (PasswordResetTokenUtils.isTokenExpired(token)) {

        token.setToken(PasswordResetTokenUtils.generateToken());
        token.setExpirationTime(PasswordResetTokenUtils.generateExpirationTime());

        passwordResetTokenRepository.saveAndFlush(token);
      }
    } else {

      PasswordResetToken newToken =
          new PasswordResetToken(
              PasswordResetTokenUtils.generateToken(),
              PasswordResetTokenUtils.generateExpirationTime(),
              user);

      token = passwordResetTokenRepository.saveAndFlush(newToken);
    }

    mailService.sendEmail(
        "Password Reset Request",
        mailTemplates.getPasswordResetTokenTemplate(user.getFirstName(), token.getToken()),
        requestDTO.getEmail());

    return ConcreteMessageResponseFactory.buildMessage("Password request sent!");
  }
}
