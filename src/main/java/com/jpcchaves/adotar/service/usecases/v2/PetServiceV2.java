package com.jpcchaves.adotar.service.usecases.v2;

import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetServiceV2 {
    ApiResponsePaginatedDto<PetMinDtoV2> listAllV2(Pageable pageable);

    ApiResponsePaginatedDto<PetMinDtoV2> filterByAnimalTypes(Pageable pageable,
                                                             List<Long> animalTypesIds);
}
