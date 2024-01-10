package com.jpcchaves.adotar.service.pet;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.service.pet.contracts.PetRepositoryService;
import com.jpcchaves.adotar.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.utils.base64.Base64Utils;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
public class PetUtilsImpl implements PetUtils {
    private final PetRepositoryService petRepositoryService;
    private final MapperUtils mapper;

    public PetUtilsImpl(PetRepositoryService petRepositoryService,
                        MapperUtils mapper) {
        this.petRepositoryService = petRepositoryService;

        this.mapper = mapper;
    }

    @Override
    public void increasePetVisualization(Pet pet) {
        int ONE = 1;
        pet.setVisualizations(pet.getVisualizations() + ONE);
    }


    @Override
    public Pet buildPet(PetCreateRequestDto petCreateRequestDto,
                        AnimalType animalType,
                        Breed breed,
                        List<PetCharacteristic> characteristicsList,
                        Address petAddress,
                        User user) {
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
        pet.setSerialNumber(generateUniqueSerialNumber());

        pet.setAddress(petAddress);
        pet.setType(animalType);
        pet.setBreed(breed);
        pet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicsList));

        pet.setUser(user);

        return pet;
    }

    @Override
    public Pet updatePet(Pet pet,
                         PetUpdateRequestDto petDto,
                         AnimalType animalType,
                         Breed breed,
                         List<PetCharacteristic> characteristicsList) {
        pet.setHealthCondition(petDto.getHealthCondition());
        pet.setGender(petDto.getGender());
        pet.setSize(petDto.getSize());
        pet.setMonthsAge(petDto.getMonthsAge());
        pet.setYearsAge(petDto.getYearsAge());
        pet.setColor(petDto.getColor());
        pet.setName(petDto.getName());
        pet.setDescription(petDto.getDescription());

        pet.setType(animalType);
        pet.setBreed(breed);
        pet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicsList));


        return pet;
    }

    @Override
    public Pet updatePetAttributes(Pet pet,
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


    @Override
    public void removeBase64Prefix(List<PetPictureDto> pictureDtos) {
        for (PetPictureDto picture : pictureDtos) {
            if (Base64Utils.hasBase64Prefix(picture.getImgUrl())) {
                picture.setImgUrl(Base64Utils.removeBase64Prefix(picture.getImgUrl()));
            }
        }
    }

    @Override
    public <T> void verifyCharacteristicsLimit(List<T> characteristics) {
        if (!isListSizeUnderLimit(characteristics)) {
            throw new BadRequestException("O limite de características foi excedido!");
        }
    }


    @Override
    public void markFavoritePets(User user,
                                 List<PetMinDtoV2> dtoList) {
        for (PetMinDtoV2 petDto : dtoList) {
            if (petRepositoryService.isPetSavedByUser(user.getId(), petDto.getId())) {
                petDto.setFavorite(true);
            }
        }
    }

    @Override
    public void setPetAsInactive(Pet pet) {
        pet.setActive(false);
        pet.setDeletedAt(new Date());
    }

    @Override
    public Set<Pet> extractPets(List<UserSavedPets> userSavedPets) {
        List<Pet> pets = new ArrayList<>();

        for (UserSavedPets userSavedPet : userSavedPets) {
            pets.add(userSavedPet.getPet());
        }

        return new HashSet<>(pets);
    }

    @Override
    public boolean breedAndAnimalTypeIsPresent(Long breedId,
                                               Long animalTypeId) {
        return breedId != null && animalTypeId != null;
    }

    @Override
    public boolean animalTypeIsPresent(Long animalTypeId) {
        return animalTypeId != null;
    }

    @Override
    public boolean breedIsPresent(Long breedId) {
        return breedId != null;
    }

    public boolean existsByPetAndUser(Long petId,
                                      Long userId) {
        return petRepositoryService.existsByPetAndUser(petId, userId);
    }

    @Override
    public List<PetMinDtoV2> doFilterByBreedAndAnimalType(Pageable pageable,
                                                          Long breedId,
                                                          Long animalTypeId) {
        Page<Pet> petsPage = petRepositoryService
                .getAllByBreedIdAndTypeId(pageable, breedId, animalTypeId);

        return mapper
                .parseListObjects(petsPage.getContent(), PetMinDtoV2.class);
    }

    @Override
    public List<PetMinDtoV2> doFilterByAnimalType(Pageable pageable,
                                                  Long animalTypeId) {
        Page<Pet> petsPage = petRepositoryService.getAllByTypeId(pageable, animalTypeId);
        return mapper.parseListObjects(petsPage.getContent(), PetMinDtoV2.class);
    }

    @Override
    public List<PetMinDtoV2> doFilterByBreed(Pageable pageable,
                                             Long breedId) {
        Page<Pet> petsPage = petRepositoryService.getAllByBreedId(pageable, breedId);
        return mapper.parseListObjects(petsPage.getContent(), PetMinDtoV2.class);
    }

    private <T> boolean isListSizeUnderLimit(List<T> list) {
        final int LIMIT = 5;
        return list.size() <= LIMIT;
    }

    private String generateUniqueSerialNumber() {
        final int length = 25;
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder serialNumber = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            serialNumber.append(randomChar);
        }

        return serialNumber.toString();
    }
}
