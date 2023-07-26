package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetMinDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PetService {
    ApiResponsePaginatedDto<PetMinDto> listAll(Pageable pageable);

    ApiResponsePaginatedDto<PetMinDto> getAllByBreed(Pageable pageable,
                                                     Long breedId,
                                                     Long animalTypeId);

    ApiResponsePaginatedDto<PetDto> getAllByUser_Id(Pageable pageable);

    PetDto getById(Long id);

    Set<PetDto> getUserSavedPets();

    ApiMessageResponseDto create(PetCreateRequestDto petDto);

    ApiMessageResponseDto update(Long id,
                                 PetUpdateRequestDto petDto);

    ApiMessageResponseDto delete(Long id);
}
