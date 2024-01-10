package com.jpcchaves.adotar.service.pet.contracts;

import com.jpcchaves.adotar.domain.entities.AnimalType;
import com.jpcchaves.adotar.domain.entities.Breed;
import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
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

    void createPetPictures(List<PetPictureCreateDto> petPicturesDto,
                           Pet pet);
}
