package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.RegisterRequestDTO;

public interface RegisterGateway {

  String register(RegisterRequestDTO requestDTO);
}
