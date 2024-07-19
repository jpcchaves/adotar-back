package com.cleanarch.usecase.role;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.role.dto.BaseRoleRequestDTO;

@FunctionalInterface
public interface CreateRoleUseCase {

  MessageResponseDTO create(BaseRoleRequestDTO requestDTO);
}
