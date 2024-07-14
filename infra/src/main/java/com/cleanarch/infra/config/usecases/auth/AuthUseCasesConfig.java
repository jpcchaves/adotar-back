package com.cleanarch.infra.config.usecases.auth;

import com.cleanarch.application.gateway.auth.RegisterGateway;
import com.cleanarch.application.usecaseimpl.auth.RegisterUseCaseImpl;
import com.cleanarch.usecase.auth.RegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCasesConfig {

  @Bean
  public RegisterUseCase registerUseCase(RegisterGateway registerGateway) {
    return new RegisterUseCaseImpl(registerGateway);
  }
}
