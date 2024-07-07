package com.cleanarch.application.gateway.pet;

import br.com.jpcchaves.core.domain.model.PaginationRequest;
import br.com.jpcchaves.core.domain.model.PaginationResponse;
import com.cleanarch.usecase.pet.dto.PetMinDTO;

public interface GetAllPetsByUserGateway {

  PaginationResponse<PetMinDTO> get(PaginationRequest paginationRequest);

}
