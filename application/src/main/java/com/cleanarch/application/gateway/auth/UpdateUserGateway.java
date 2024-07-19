package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.BaseUpdateUserRequestDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;

@FunctionalInterface
public interface UpdateUserGateway {

  MessageResponseDTO updateUser(BaseUpdateUserRequestDTO requestDTO);
}
