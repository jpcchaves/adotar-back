package com.jpcchaves.adotar.factory.jwt;

import com.jpcchaves.adotar.payload.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.payload.dto.user.UserDto;

public interface JwtAuthResponseFactory {
    JwtAuthResponseDto createJwtAuthResponse(String token, UserDto userDto);
}
