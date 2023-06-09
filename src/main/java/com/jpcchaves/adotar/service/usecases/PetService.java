package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {
    ApiResponsePaginatedDto<PetDto> listAll(Pageable pageable);
}
