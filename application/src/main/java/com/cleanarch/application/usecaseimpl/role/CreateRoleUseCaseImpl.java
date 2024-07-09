package com.cleanarch.application.usecaseimpl.role;

import com.cleanarch.application.gateway.role.CreateRoleGateway;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import com.cleanarch.usecase.role.CreateRoleUseCase;
import com.cleanarch.usecase.role.dto.RoleRequestDTO;

public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

  private final CreateRoleGateway createRoleGateway;

  public CreateRoleUseCaseImpl(CreateRoleGateway createRoleGateway) {
    this.createRoleGateway = createRoleGateway;
  }

  @Override
  public MessageResponseDTO create(RoleRequestDTO requestDTO) {
    return createRoleGateway.create(requestDTO);
  }
}
