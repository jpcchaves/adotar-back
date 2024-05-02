package com.jpcchaves.adotar.infrastructure.factory.jwt;

import com.jpcchaves.adotar.infrastructure.application.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ConcreteJwtAuthResponseFactory implements JwtAuthResponseFactory {

  @Override
  public JwtAuthResponseDto createJwtAuthResponse(
      String token, UserDto userDto) {
    return new JwtAuthResponseDto(token, userDto);
  }
}
