package com.cleanarch.infra.config.usecases.auth;

import com.cleanarch.application.gateway.auth.LoginGateway;
import com.cleanarch.application.gateway.auth.RegisterGateway;
import com.cleanarch.application.gateway.auth.UpdatePasswordGateway;
import com.cleanarch.application.gateway.auth.UpdateUserGateway;
import com.cleanarch.application.usecaseimpl.auth.LoginUseCaseImpl;
import com.cleanarch.application.usecaseimpl.auth.RegisterUseCaseImpl;
import com.cleanarch.application.usecaseimpl.auth.UpdatePasswordUseCaseImpl;
import com.cleanarch.application.usecaseimpl.auth.UpdateUserUseCaseImpl;
import com.cleanarch.usecase.auth.LoginUseCase;
import com.cleanarch.usecase.auth.RegisterUseCase;
import com.cleanarch.usecase.auth.UpdatePasswordUseCase;
import com.cleanarch.usecase.auth.UpdateUserUseCase;
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
}
