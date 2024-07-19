package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.pet.dto.PetDTO;

@FunctionalInterface
public interface GetPetByIdGateway {

  PetDTO getById(Long petId);
}
