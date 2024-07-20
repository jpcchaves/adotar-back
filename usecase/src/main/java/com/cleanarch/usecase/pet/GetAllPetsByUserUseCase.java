package com.cleanarch.usecase.pet;

import br.com.jpcchaves.core.domain.model.PaginationRequest;
import br.com.jpcchaves.core.domain.model.PaginationResponse;
import com.cleanarch.usecase.pet.dto.PetMinDTO;

@FunctionalInterface
public interface GetAllPetsByUserUseCase {

  PaginationResponse<PetMinDTO> get(PaginationRequest paginationRequest);

}
