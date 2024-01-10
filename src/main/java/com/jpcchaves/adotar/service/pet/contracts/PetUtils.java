package com.jpcchaves.adotar.service.pet.contracts;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;

import java.util.List;

public interface PetUtils {
    void increasePetVisualization(Pet pet);

    Pet buildPet(PetCreateRequestDto petCreateRequestDto,
                 AnimalType animalType,
                 Breed breed,
                 List<PetCharacteristic> characteristicsList,
                 Address petAddress,
                 User user);

    Pet updatePet(Pet pet,
                  PetUpdateRequestDto petDto,
                  AnimalType animalType,
                  Breed breed,
                  List<PetCharacteristic> characteristicsList
    );

    Pet updatePetAttributes(Pet pet,
                            PetUpdateRequestDto petDto);

    void removeBase64Prefix(List<PetPictureDto> pictureDtos);

    <T> void verifyCharacteristicsLimit(List<T> characteristics);

    void markFavoritePets(
            User user,
            List<PetMinDtoV2> dtoList
    );

    void setPetAsInactive(Pet pet);

}
