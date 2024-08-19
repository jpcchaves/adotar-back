package com.cleanarch.infra.config.usecases.auth;

import com.cleanarch.application.gateway.auth.*;
import com.cleanarch.application.usecaseimpl.auth.*;
import com.cleanarch.usecase.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCasesConfig {

  @Bean
  public RegisterUseCase registerUseCase(RegisterGateway registerGateway) {

    return new RegisterUseCaseImpl(registerGateway);
  }

  @Bean
  public LoginUseCase loginUseCase(LoginGateway loginGateway) {

    return new LoginUseCaseImpl(loginGateway);
  }

  @Bean
  public UpdatePasswordUseCase updatePasswordUseCase(
      UpdatePasswordGateway updatePasswordGateway) {

    return new UpdatePasswordUseCaseImpl(updatePasswordGateway);
  }

  @Bean
  public UpdateUserUseCase updateUserUseCase(
      UpdateUserGateway updateUserGateway) {

    return new UpdateUserUseCaseImpl(updateUserGateway);
  }

  @Bean
  public RequestPasswordResetUseCase requestPasswordResetUseCase(
      RequestPasswordResetGateway requestPasswordResetGateway) {

    return new RequestPasswordResetUseCaseImpl(requestPasswordResetGateway);
  }
}
