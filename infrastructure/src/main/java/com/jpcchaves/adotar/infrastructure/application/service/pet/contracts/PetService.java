package com.jpcchaves.adotar.infrastructure.application.service.pet.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.PetDetailsDto;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.PetDto;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.v2.PetDtoV2;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDetailsDto;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface PetService {
  ApiResponsePaginatedDto<PetMinDtoV2> listAll(Pageable pageable);

  ApiResponsePaginatedDto<PetMinDtoV2> filterByBreedOrAnimalType(
      Pageable pageable, Long breedId, Long animalTypeId);

  ApiResponsePaginatedDto<PetMinDtoV2> getAllByUserId(Pageable pageable);

  PetDtoV2 getById(Long id);

  PetDetailsDto getPetDetails(Long id);

  PetDto getBySerialNumber(String serialNumber);

  Set<PetDto> getUserSavedPets();

  UserDetailsDto getPetOwnerDetails(Long petId);

  ApiMessageResponseDto addUserSavedPet(Long petId);

  ApiMessageResponseDto removeUserSavedPet(Long petId);

  ApiMessageResponseDto create(PetCreateRequestDto petDto);

  ApiMessageResponseDto update(Long id, PetUpdateRequestDto petDto);

  ApiMessageResponseDto delete(Long id);

  ApiResponsePaginatedDto<PetMinDtoV2> filterByAnimalTypes(
      Pageable pageable, List<Long> animalTypesIds);
}
