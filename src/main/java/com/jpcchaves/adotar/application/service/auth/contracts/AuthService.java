package com.jpcchaves.adotar.application.service.auth.contracts;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.auth.*;

public interface AuthService {
    JwtAuthResponseDto login(LoginDto loginDto);

    JwtAuthResponseDto loginV2(LoginDtoV2 loginDtoV2);

    RegisterResponseDto register(RegisterRequestDto registerDto);

    ApiMessageResponseDto update(
            UpdateUserRequestDto updateUserDto,
            Long id);

    ApiMessageResponseDto updatePassword(
            UpdateUserPasswordRequestDTO requestDTO
    );

    JwtAuthResponseDto verifyToken(TokenDto tokenDto);
}
