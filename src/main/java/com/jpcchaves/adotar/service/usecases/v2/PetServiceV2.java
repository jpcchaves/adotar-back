package com.jpcchaves.adotar.service.usecases.v2;

import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import org.springframework.data.domain.Pageable;

public interface PetServiceV2 {
    ApiResponsePaginatedDto<PetMinDtoV2> listAllV2(Pageable pageable);
}
