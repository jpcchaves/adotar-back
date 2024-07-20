package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;

@FunctionalInterface
public interface RegisterGateway {

  String register(BaseRegisterRequestDTO requestDTO);
}
