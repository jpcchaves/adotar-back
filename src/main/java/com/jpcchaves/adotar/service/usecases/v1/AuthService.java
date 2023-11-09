package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.*;

public interface AuthService {
    JwtAuthResponseDto login(LoginDto loginDto);

    JwtAuthResponseDto loginV2(LoginDtoV2 loginDtoV2);

    RegisterResponseDto register(RegisterRequestDto registerDto);

    ApiMessageResponseDto update(
            UpdateUserRequestDto updateUserDto,
            Long id);

    JwtAuthResponseDto verifyToken(TokenDto tokenDto);
}
