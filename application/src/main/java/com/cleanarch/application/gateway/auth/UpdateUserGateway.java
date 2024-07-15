package com.cleanarch.application.gateway.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.common.dto.*;

public interface UpdateUserGateway {

  MessageResponseDTO updateUser(BaseUpdateUserRequestDTO requestDTO);
}
