package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.LoginRequestDTO;

public interface LoginGateway {

  String login(LoginRequestDTO requestDTO);
}
