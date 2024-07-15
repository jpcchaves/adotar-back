package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.*;

public interface LoginGateway {

  LoginResponseDTO login(BaseLoginRequestDTO requestDTO);
}
