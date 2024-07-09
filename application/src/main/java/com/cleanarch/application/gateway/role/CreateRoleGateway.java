package com.cleanarch.application.gateway.role;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.role.dto.RoleRequestDTO;

public interface CreateRoleGateway {
  
  MessageResponseDTO create(RoleRequestDTO requestDTO);
}