package com.cleanarch.application.gateway.pet;

import br.com.jpcchaves.core.domain.model.PaginationRequest;
import br.com.jpcchaves.core.domain.model.PaginationResponse;
import com.cleanarch.usecase.pet.dto.PetMinDTO;

@FunctionalInterface
public interface ListPetsGateway {

  PaginationResponse<PetMinDTO> list(PaginationRequest pagination);
}
