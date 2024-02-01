package com.jpcchaves.adotar.application.service.pet.contracts;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.AnimalType;
import com.jpcchaves.adotar.domain.model.Breed;
import com.jpcchaves.adotar.domain.model.Pet;
import com.jpcchaves.adotar.domain.model.PetCharacteristic;
import com.jpcchaves.adotar.domain.model.PetPicture;
import com.jpcchaves.adotar.domain.model.User;
import com.jpcchaves.adotar.domain.model.UserSavedPets;
import com.jpcchaves.adotar.application.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.application.dto.pet.PetPictureMinDto;
import com.jpcchaves.adotar.application.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.application.dto.pet.v2.PetMinDtoV2;

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
                  List<PetCharacteristic> characteristicsList,
                  Address petAddress
    );

    Pet updatePetAttributes(Pet pet,
                            PetUpdateRequestDto petDto);

    void removeBase64Prefix(List<PetPictureMinDto> pictureDtos);

    <T> void verifyCharacteristicsLimit(List<T> characteristics);

    void markFavoritePets(
            User user,
            List<PetMinDtoV2> dtoList
    );

    void setPetAsInactive(Pet pet);

    boolean existsByPetAndUser(Long petId,
                               Long userId);

    Set<Pet> extractPets(List<UserSavedPets> userSavedPets);

    boolean breedAndAnimalTypeIsPresent(Long breedId,
                                        Long animalTypeId);

    boolean animalTypeIsPresent(Long animalTypeId);

    boolean breedIsPresent(Long breedId);

    Page<Pet> doFilterByBreedAndAnimalType(Pageable pageable,
                                           Long breedId,
                                           Long animalTypeId);

    Page<Pet> doFilterByAnimalType(Pageable pageable,
                                   Long animalTypeId);

    Page<Pet> doFilterByBreed(Pageable pageable,
                              Long breedId);

    Page<Pet> filterPets(Pageable pageable,
                         Long breedId,
                         Long animalTypeId);

    void setPetPictures(Pet pet,
                        List<PetPicture> petPictures);
}
