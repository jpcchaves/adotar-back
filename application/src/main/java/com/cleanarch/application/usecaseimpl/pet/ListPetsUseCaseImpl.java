package com.cleanarch.application.usecaseimpl.pet;

import br.com.jpcchaves.core.domain.model.PaginationRequest;
import br.com.jpcchaves.core.domain.model.PaginationResponse;
import com.cleanarch.application.gateway.pet.ListPetsGateway;
import com.cleanarch.usecase.pet.ListPetsUseCase;
import com.cleanarch.usecase.pet.dto.PetMinDTO;

public class ListPetsUseCaseImpl implements ListPetsUseCase {

  private final ListPetsGateway listPetsGateway;

  public ListPetsUseCaseImpl(ListPetsGateway listPetsGateway) {
    this.listPetsGateway = listPetsGateway;
  }

  @Override
  public PaginationResponse<PetMinDTO> list(PaginationRequest pagination) {
    return listPetsGateway.list(pagination);
  }
}
