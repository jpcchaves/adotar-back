package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import org.springframework.data.domain.Pageable;

public interface PetService {
    ApiResponsePaginatedDto<PetDto> listAll(Pageable pageable);

    PetDto getById(Long id);

    ApiMessageResponseDto create(PetCreateRequestDto petDto);
}
