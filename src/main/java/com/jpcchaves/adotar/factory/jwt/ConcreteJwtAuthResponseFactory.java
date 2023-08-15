package com.jpcchaves.adotar.factory.jwt;

import com.jpcchaves.adotar.payload.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.payload.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ConcreteJwtAuthResponseFactory implements JwtAuthResponseFactory {

    @Override
    public JwtAuthResponseDto createJwtAuthResponse(String token, UserDto userDto) {
        return new JwtAuthResponseDto(token, userDto);
    }
}
