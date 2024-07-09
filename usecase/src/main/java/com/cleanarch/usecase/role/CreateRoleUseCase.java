package com.cleanarch.usecase.role;

import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.role.dto.*;

public interface CreateRoleUseCase {
  MessageResponseDTO create(CreateRoleRequestDTO requestDTO);
}
