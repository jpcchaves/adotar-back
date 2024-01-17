package com.jpcchaves.adotar.service.pet.contracts;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDetailsDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetDtoV2;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface PetService {
    ApiResponsePaginatedDto<PetMinDtoV2> listAll(Pageable pageable);

    ApiResponsePaginatedDto<PetMinDtoV2> filterByBreedOrAnimalType(Pageable pageable,
                                                                   Long breedId,
                                                                   Long animalTypeId);

    ApiResponsePaginatedDto<PetMinDtoV2> getAllByUserId(Pageable pageable);

    PetDtoV2 getById(Long id);

    PetDetailsDto getPetDetails(Long id);

    PetDto getBySerialNumber(String serialNumber);

    Set<PetDto> getUserSavedPets();

    UserDetailsDto getPetOwnerDetails(Long petId);

    ApiMessageResponseDto addUserSavedPet(Long petId);

    ApiMessageResponseDto removeUserSavedPet(Long petId);

    ApiMessageResponseDto create(PetCreateRequestDto petDto);

    ApiMessageResponseDto update(Long id,
                                 PetUpdateRequestDto petDto);

    ApiMessageResponseDto delete(Long id);

    ApiResponsePaginatedDto<PetMinDtoV2> filterByAnimalTypes(Pageable pageable,
                                                             List<Long> animalTypesIds);

}
