package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.common.dto.*;

@FunctionalInterface
public interface RequestPasswordResetGateway {

  MessageResponseDTO resetTokenRequest(BasePasswordResetRequestDTO requestDTO);
}
