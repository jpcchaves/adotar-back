package com.cleanarch.usecase.role;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.role.dto.*;

@FunctionalInterface
public interface UpdateRoleUseCase {

  MessageResponseDTO update(Long roleId, RoleRequestDTO requestDTO);
}
