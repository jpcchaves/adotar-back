package com.cleanarch.infra.gateway.auth;

import br.com.jpcchaves.core.exception.AuthException;
import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.application.gateway.auth.LoginGateway;
import com.cleanarch.infra.config.security.JwtTokenProvider;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.factory.auth.AuthFactory;
import com.cleanarch.infra.repository.UserRepository;
import com.cleanarch.usecase.auth.dto.BaseLoginRequestDTO;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginGatewayImpl implements LoginGateway {

  private static final Logger logger = LoggerFactory.getLogger(
      LoginGatewayImpl.class);

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final AuthFactory authFactory;
  private final JwtTokenProvider jwtTokenProvider;

  public LoginGatewayImpl(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      AuthFactory authFactory,
      JwtTokenProvider jwtTokenProvider
  ) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.authFactory = authFactory;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public LoginResponseDTO login(BaseLoginRequestDTO requestDTO) {

    try {

      User user = userRepository
          .findByEmail(requestDTO.getEmail())
          .orElseThrow(
              () -> new BadRequestException(ExceptionDefinition.USR0001));

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          requestDTO.getEmail(), requestDTO.getPassword());

      Authentication authentication = authenticationManager.authenticate(
          authenticationToken
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenProvider.generateToken(authentication);

      return authFactory.buildLoginReponse(token, user);

    } catch (AuthenticationException ex) {

      logger.error(ex.getMessage());

      throw new AuthException(ExceptionDefinition.AUT0001);
    }
  }
}
