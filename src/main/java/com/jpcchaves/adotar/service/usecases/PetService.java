package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetMinDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PetService {
    ApiResponsePaginatedDto<PetMinDto> listAll(Pageable pageable);

    ApiResponsePaginatedDto<PetMinDto> filterByBreedOrAnimalType(Pageable pageable,
                                                                 Long breedId,
                                                                 Long animalTypeId);

    ApiResponsePaginatedDto<PetDto> getAllByUserId(Pageable pageable);

    PetDto getById(Long id);

    PetDto getBySerialNumber(String serialNumber);

    Set<PetDto> getUserSavedPets();

    UserDetailsDto getPetOwnerDetails(Long petId);

    ApiMessageResponseDto addUserSavedPet(Long petId);

    ApiMessageResponseDto removeUserSavedPet(Long petId);

    ApiMessageResponseDto create(PetCreateRequestDto petDto);

    ApiMessageResponseDto update(Long id,
                                 PetUpdateRequestDto petDto);

    ApiMessageResponseDto delete(Long id);
}
