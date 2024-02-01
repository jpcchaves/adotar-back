package com.jpcchaves.adotar.factory.jwt;

import com.jpcchaves.adotar.application.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.application.dto.user.UserDto;

public interface JwtAuthResponseFactory {
    JwtAuthResponseDto createJwtAuthResponse(String token, UserDto userDto);
}
