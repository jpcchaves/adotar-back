package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.BaseRegisterRequestDTO;

public interface RegisterGateway {

  String register(BaseRegisterRequestDTO requestDTO);
}
