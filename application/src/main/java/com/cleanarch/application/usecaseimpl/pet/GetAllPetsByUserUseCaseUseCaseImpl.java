package com.cleanarch.application.usecaseimpl.pet;

import br.com.jpcchaves.core.domain.model.PaginationRequest;
import br.com.jpcchaves.core.domain.model.PaginationResponse;
import com.cleanarch.application.gateway.pet.GetAllPetsByUserGateway;
import com.cleanarch.usecase.pet.GetAllPetsByUserUseCase;
import com.cleanarch.usecase.pet.dto.PetMinDTO;

public class GetAllPetsByUserUseCaseUseCaseImpl implements
    GetAllPetsByUserUseCase {

  private final GetAllPetsByUserGateway getAllPetsByUserGateway;

  public GetAllPetsByUserUseCaseUseCaseImpl(GetAllPetsByUserGateway getAllPetsByUserGateway) {
    this.getAllPetsByUserGateway = getAllPetsByUserGateway;
  }

  @Override
  public PaginationResponse<PetMinDTO> get(PaginationRequest paginationRequest) {
    return getAllPetsByUserGateway.get(paginationRequest);
  }
}
