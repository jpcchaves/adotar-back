package com.cleanarch.application.usecaseimpl.pet;

import com.cleanarch.application.gateway.pet.GetPetByIdGateway;
import com.cleanarch.usecase.pet.GetPetByIdUseCase;
import com.cleanarch.usecase.pet.dto.PetDTO;

public class GetPetByIdUseCaseImpl implements GetPetByIdUseCase {

  private final GetPetByIdGateway getPetByIdGateway;

  public GetPetByIdUseCaseImpl(GetPetByIdGateway getPetByIdGateway) {
    this.getPetByIdGateway = getPetByIdGateway;
  }

  @Override
  public PetDTO getById(Long petId) {
    return getPetByIdGateway.getById(petId);
  }
}
