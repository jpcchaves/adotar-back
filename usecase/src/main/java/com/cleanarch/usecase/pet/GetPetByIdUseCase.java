package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.pet.dto.PetDTO;

@FunctionalInterface
public interface GetPetByIdUseCase {

  PetDTO getById(Long petId);
}
