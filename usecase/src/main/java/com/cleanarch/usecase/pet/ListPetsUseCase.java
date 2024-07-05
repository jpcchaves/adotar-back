package com.cleanarch.usecase.pet;

import br.com.jpcchaves.core.domain.model.*;
import com.cleanarch.usecase.pet.dto.*;

public interface ListPetsUseCase {
  PaginationResponse<PetMinDTO> list(PaginationRequest pagination);
}
