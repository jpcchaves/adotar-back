package com.jpcchaves.adotar.infrastructure.application.service.auth.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.auth.*;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.LoginDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.LoginDtoV2;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.RegisterRequestDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.RegisterResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.TokenDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.UpdateUserPasswordRequestDTO;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.UpdateUserRequestDto;

public interface AuthService {
  JwtAuthResponseDto login(LoginDto loginDto);

  JwtAuthResponseDto loginV2(LoginDtoV2 loginDtoV2);

  RegisterResponseDto register(RegisterRequestDto registerDto);

  ApiMessageResponseDto update(UpdateUserRequestDto updateUserDto, Long id);

  ApiMessageResponseDto updatePassword(UpdateUserPasswordRequestDTO requestDTO);

  JwtAuthResponseDto verifyToken(TokenDto tokenDto);
}
