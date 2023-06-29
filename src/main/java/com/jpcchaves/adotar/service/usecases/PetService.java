package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import org.springframework.data.domain.Pageable;

public interface PetService {
    ApiResponsePaginatedDto<PetDto> listAll(Pageable pageable);

    ApiResponsePaginatedDto<PetDto> getAllByBreed(Pageable pageable,
                                                  Long breedId,
                                                  Long animalTypeId);

    ApiResponsePaginatedDto<PetDto> getAllByUser_Id(Pageable pageable,
                                                    Long userId);

    PetDto getPetByIdAndUser_Id(Long petId,
                                Long userId);

    PetDto getById(Long id);

    ApiMessageResponseDto create(PetCreateRequestDto petDto);

    ApiMessageResponseDto update(Long id,
                                 PetUpdateRequestDto petDto);

    ApiMessageResponseDto delete(Long id);
}
