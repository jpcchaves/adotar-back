package com.cleanarch.application.usecaseimpl.role;

import com.cleanarch.application.gateway.role.*;
import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.role.*;
import com.cleanarch.usecase.role.dto.*;

public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

  private final UpdateRoleGateway updateRoleGateway;

  public UpdateRoleUseCaseImpl(UpdateRoleGateway updateRoleGateway) {
    this.updateRoleGateway = updateRoleGateway;
  }

  @Override
  public MessageResponseDTO update(
      Long roleId,
      BaseRoleRequestDTO requestDTO
  ) {
    return updateRoleGateway.update(roleId, requestDTO);
  }
}
