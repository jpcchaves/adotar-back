package com.cleanarch.application.usecaseimpl.role;

import com.cleanarch.application.gateway.role.UpdateRoleGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.role.UpdateRoleUseCase;
import com.cleanarch.usecase.role.dto.RoleRequestDTO;

public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

  private final UpdateRoleGateway updateRoleGateway;

  public UpdateRoleUseCaseImpl(UpdateRoleGateway updateRoleGateway) {
    this.updateRoleGateway = updateRoleGateway;
  }

  @Override
  public MessageResponseDTO update(
      Long roleId,
      RoleRequestDTO requestDTO
  ) {
    return updateRoleGateway.update(roleId, requestDTO);
  }
}
