package com.cleanarch.application.usecaseimpl.role;

import com.cleanarch.application.gateway.role.*;
import com.cleanarch.usecase.common.dto.*;
import com.cleanarch.usecase.role.*;
import com.cleanarch.usecase.role.dto.*;

public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

  private final CreateRoleGateway createRoleGateway;

  public CreateRoleUseCaseImpl(CreateRoleGateway createRoleGateway) {
    this.createRoleGateway = createRoleGateway;
  }

  @Override
  public MessageResponseDTO create(BaseRoleRequestDTO requestDTO) {
    return createRoleGateway.create(requestDTO);
  }
}
