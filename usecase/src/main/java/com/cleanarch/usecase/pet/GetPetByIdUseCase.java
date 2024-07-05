package com.cleanarch.usecase.pet;

import com.cleanarch.usecase.pet.dto.*;

public interface GetPetByIdUseCase {
  PetDTO getById(Long petId);
}
