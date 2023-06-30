package com.jpcchaves.adotar.utils.pet;

import com.jpcchaves.adotar.domain.entities.AnimalType;
import com.jpcchaves.adotar.domain.entities.Breed;
import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;

import java.util.List;

public class PetUtils {
    public static void increasePetVisualization(Pet pet) {
        int ONE = 1;
        pet.setVisualizations(pet.getVisualizations() + ONE);
    }

    public static Pet buildPetCreate(PetCreateRequestDto petCreateRequestDto,
                                     AnimalType animalType,
                                     Breed breed,
                                     List<PetCharacteristic> characteristicsList) {
        Pet pet = new Pet();

        pet.setHealthCondition(petCreateRequestDto.getHealthCondition());
        pet.setGender(petCreateRequestDto.getGender());
        pet.setSize(petCreateRequestDto.getSize());
        pet.setActive(true);
        pet.setAvailable(true);
        pet.setMonthsAge(petCreateRequestDto.getMonthsAge());
        pet.setYearsAge(petCreateRequestDto.getYearsAge());
        pet.setVisualizations(0);
        pet.setColor(petCreateRequestDto.getColor());
        pet.setName(petCreateRequestDto.getName());
        pet.setDescription(petCreateRequestDto.getDescription());

        pet.setType(animalType);
        pet.setBreed(breed);
        pet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicsList));

        return pet;
    }

    public static Pet updatePetAttributes(Pet pet,
                                          PetUpdateRequestDto petDto) {
        pet.setId(pet.getId());
        pet.setName(petDto.getName());
        pet.setYearsAge(petDto.getYearsAge());
        pet.setMonthsAge(petDto.getMonthsAge());
        pet.setGender(petDto.getGender());
        pet.setSize(petDto.getSize());
        pet.setHealthCondition(petDto.getHealthCondition());
        pet.setColor(petDto.getColor());
        pet.setDescription(petDto.getDescription());
        pet.setVisualizations(pet.getVisualizations());
        pet.setAvailable(petDto.isAvailable());
        pet.setAdoptionDate(petDto.getAdoptionDate());

        return pet;
    }
}
