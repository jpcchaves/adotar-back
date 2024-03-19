package com.jpcchaves.adotar.application.service.pet;

import com.jpcchaves.adotar.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.application.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.application.dto.pet.PetPictureMinDto;
import com.jpcchaves.adotar.application.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.application.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.application.service.pet.contracts.PetRepositoryService;
import com.jpcchaves.adotar.application.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.application.utils.base64.Base64Utils;
import com.jpcchaves.adotar.application.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import com.jpcchaves.adotar.domain.model.*;
import com.jpcchaves.adotar.factory.address.StaticAbstractAddressFactory;
import java.security.SecureRandom;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetUtilsImpl implements PetUtils {
  private final PetRepositoryService petRepositoryService;

  public PetUtilsImpl(PetRepositoryService petRepositoryService) {
    this.petRepositoryService = petRepositoryService;
  }

  @Override
  public void increasePetVisualization(Pet pet) {
    int ONE = 1;
    pet.setVisualizations(pet.getVisualizations() + ONE);
  }

  @Override
  public Pet buildPet(
      PetCreateRequestDto petCreateRequestDto,
      AnimalType animalType,
      Breed breed,
      List<PetCharacteristic> characteristicsList,
      Address petAddress,
      User user) {
    Pet pet = new Pet();

    HealthCondition healthCondition =
        HealthCondition.fromValue(petCreateRequestDto.getHealthCondition());
    AnimalGender animalGender =
        AnimalGender.fromValue(petCreateRequestDto.getGender());
    AnimalSize animalSize = AnimalSize.fromValue(petCreateRequestDto.getSize());

    pet.setHealthCondition(healthCondition);
    pet.setGender(animalGender);
    pet.setSize(animalSize);

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
    pet.setCharacteristics(
        CollectionsUtils.convertListToSet(characteristicsList));

    pet.setUser(user);

    return pet;
  }

  @Override
  public Pet updatePet(
      Pet pet,
      PetUpdateRequestDto petDto,
      AnimalType animalType,
      Breed breed,
      List<PetCharacteristic> characteristicsList,
      Address petAddress) {
    HealthCondition healthCondition =
        HealthCondition.fromValue(petDto.getHealthCondition());
    AnimalGender animalGender = AnimalGender.fromValue(petDto.getGender());
    AnimalSize animalSize = AnimalSize.fromValue(petDto.getSize());

    pet.setHealthCondition(healthCondition);
    pet.setGender(animalGender);
    pet.setSize(animalSize);

    pet.setMonthsAge(petDto.getMonthsAge());
    pet.setYearsAge(petDto.getYearsAge());
    pet.setColor(petDto.getColor());
    pet.setName(petDto.getName());
    pet.setDescription(petDto.getDescription());

    pet.setType(animalType);
    pet.setBreed(breed);
    pet.setCharacteristics(
        CollectionsUtils.convertListToSet(characteristicsList));

    Address address = pet.getAddress();

    address.setCity(petAddress.getCity());
    address.setState(petAddress.getState());
    address.setZipcode(petAddress.getZipcode());
    address.setStreet(petAddress.getStreet());
    address.setNumber(petAddress.getNumber());
    address.setComplement(petAddress.getComplement());
    address.setNeighborhood(petAddress.getNeighborhood());

    return pet;
  }

  @Override
  public Pet updatePetAttributes(Pet pet, PetUpdateRequestDto petDto) {
    pet.setId(pet.getId());
    pet.setName(petDto.getName());
    pet.setYearsAge(petDto.getYearsAge());
    pet.setMonthsAge(petDto.getMonthsAge());

    HealthCondition healthCondition =
        HealthCondition.fromValue(petDto.getHealthCondition());
    AnimalGender animalGender = AnimalGender.fromValue(petDto.getGender());
    AnimalSize animalSize = AnimalSize.fromValue(petDto.getSize());

    pet.setHealthCondition(healthCondition);
    pet.setGender(animalGender);
    pet.setSize(animalSize);

    pet.setColor(petDto.getColor());
    pet.setDescription(petDto.getDescription());
    pet.setVisualizations(pet.getVisualizations());
    pet.setAvailable(petDto.isAvailable());
    pet.setAdoptionDate(petDto.getAdoptionDate());

    return pet;
  }

  @Override
  public void removeBase64Prefix(List<PetPictureMinDto> pictureDtos) {
    for (PetPictureMinDto picture : pictureDtos) {
      if (Base64Utils.hasBase64Prefix(picture.getImgUrl())) {
        picture.setImgUrl(Base64Utils.removeBase64Prefix(picture.getImgUrl()));
      }
    }
  }

  @Override
  public <T> void verifyCharacteristicsLimit(List<T> characteristics) {
    if (!isListSizeUnderLimit(characteristics)) {
      throw new BadRequestException(
          "O limite de características foi excedido!");
    }
  }

  @Override
  public void markFavoritePets(User user, List<PetMinDtoV2> dtoList) {
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
  public boolean breedAndAnimalTypeIsPresent(Long breedId, Long animalTypeId) {
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

  public boolean existsByPetAndUser(Long petId, Long userId) {
    return petRepositoryService.existsByPetAndUser(petId, userId);
  }

  @Override
  public Page<Pet> doFilterByBreedAndAnimalType(
      Pageable pageable, Long breedId, Long animalTypeId) {
    return petRepositoryService.getAllByBreedIdAndTypeId(
        pageable, breedId, animalTypeId);
  }

  @Override
  public Page<Pet> doFilterByAnimalType(Pageable pageable, Long animalTypeId) {
    return petRepositoryService.getAllByTypeId(pageable, animalTypeId);
  }

  @Override
  public Page<Pet> doFilterByBreed(Pageable pageable, Long breedId) {
    return petRepositoryService.getAllByBreedId(pageable, breedId);
  }

  @Override
  public Page<Pet> filterPets(
      Pageable pageable, Long breedId, Long animalTypeId) {
    if (breedAndAnimalTypeIsPresent(breedId, animalTypeId)) {
      return doFilterByBreedAndAnimalType(pageable, breedId, animalTypeId);
    }

    if (animalTypeIsPresent(animalTypeId)) {
      return doFilterByAnimalType(pageable, animalTypeId);
    }

    if (breedIsPresent(breedId)) {
      return doFilterByBreed(pageable, breedId);
    }

    throw new IllegalArgumentException(
        "Combinação inválida de raça e tipo de animal");
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

  @Override
  public void setPetPictures(Pet pet, List<PetPicture> newPetPictures) {
    pet.getPetPictures().forEach(picture -> picture.setPet(null));
    pet.getPetPictures().clear();

    newPetPictures.forEach(picture -> picture.setPet(pet));
    pet.getPetPictures().addAll(newPetPictures);
  }

  @Override
  public AddressResponseDto prepareAddressResponseDto(
      City city, Address address) {
    AddressResponseDto petAddressDto =
        StaticAbstractAddressFactory
            .createAddressResponseDtoWithDefaultValues();

    petAddressDto.setCity(city.getIbge().toString());
    petAddressDto.setCityName(city.getName());
    petAddressDto.setZipcode(address.getZipcode());
    petAddressDto.setStateName(city.getState().getName());
    petAddressDto.setState(city.getState().getId().toString());
    petAddressDto.setNeighborhood(address.getNeighborhood());
    petAddressDto.setStreet(address.getStreet());
    petAddressDto.setNumber(address.getNumber());
    petAddressDto.setComplement(address.getComplement());

    return petAddressDto;
  }

  @Override
  public String preparePetOwnerName(String firstName, String lastName) {
    return String.format("%s %s", firstName, lastName);
  }
}
