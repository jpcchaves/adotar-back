package com.jpcchaves.adotar.infrastructure.factory.jwt;

import com.jpcchaves.adotar.infrastructure.application.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDto;

public interface JwtAuthResponseFactory {
  JwtAuthResponseDto createJwtAuthResponse(String token, UserDto userDto);
}
