package com.cleanarch.application.gateway.pet;

import com.cleanarch.usecase.pet.dto.PetDTO;

public interface GetPetByIdGateway {

  PetDTO getById(Long petId);
}
