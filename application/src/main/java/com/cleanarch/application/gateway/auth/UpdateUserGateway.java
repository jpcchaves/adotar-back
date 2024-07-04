package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.dto.*;

public interface UpdateUserGateway {

  MessageResponseDTO updateUser(
      UpdateUserRequestDTO requestDTO);
}
