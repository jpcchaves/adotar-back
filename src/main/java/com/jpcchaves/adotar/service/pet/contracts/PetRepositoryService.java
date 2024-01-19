package com.jpcchaves.adotar.service.pet.contracts;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetRepositoryService {
    boolean isPetSavedByUser(
            Long userId,
            Long petId);

    Page<Pet> listAll(Pageable pageable);

    Pet findById(Long id);

    Pet savePet(Pet pet);

    Page<Pet> findByAnimalTypes(Pageable pageable,
                                List<Long> animalTypesIds);

    Breed fetchBreed(Long breedId,
                     Long animalTypeId);

    List<PetCharacteristic> fetchCharacteristics(List<Long> characteristicsIds);

    AnimalType fetchAnimalType(Long animalTypeId);

    List<UserSavedPets> fetchAllUserSavedPets(Long userId);

    Page<Pet> fetchAllByUser(Pageable pageable,
                             Long userId);

    Pet findBySerialNumber(String serialNumber);

    boolean existsByPetAndUser(Long petId,
                               Long userId);

    UserSavedPets findSavedPetByPetAndUser(Long petId,
                                           Long userId);

    UserSavedPets saveUserSavedPet(UserSavedPets userSavedPet);

    void removeUserSavedPet(UserSavedPets userSavedPets);

    Page<Pet> getAllByBreedIdAndTypeId(Pageable pageable,
                                       Long breedId,
                                       Long typeId);

    Page<Pet> getAllByTypeId(Pageable pageable,
                             Long typeId);

    Page<Pet> getAllByBreedId(Pageable pageable,
                              Long breedId);
}
