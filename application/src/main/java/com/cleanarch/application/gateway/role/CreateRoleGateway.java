package com.cleanarch.application.gateway.role;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.role.dto.*;

public interface CreateRoleGateway {

  MessageResponseDTO create(BaseRoleRequestDTO requestDTO);
}
