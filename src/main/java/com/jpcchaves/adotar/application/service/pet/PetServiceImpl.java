package com.jpcchaves.adotar.application.service.pet;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.application.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.application.dto.pet.PetDetailsDto;
import com.jpcchaves.adotar.application.dto.pet.PetDto;
import com.jpcchaves.adotar.application.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.application.dto.pet.v2.PetDtoV2;
import com.jpcchaves.adotar.application.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.application.service.address.contracts.AddressService;
import com.jpcchaves.adotar.application.service.pet.contracts.PetRepositoryService;
import com.jpcchaves.adotar.application.service.pet.contracts.PetService;
import com.jpcchaves.adotar.application.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.application.service.pet.contracts.PetValidationService;
import com.jpcchaves.adotar.application.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.application.utils.global.GlobalUtils;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.application.utils.user.UserUtils;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import com.jpcchaves.adotar.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepositoryService petRepositoryService;
    private final PetValidationService petValidationService;
    private final SecurityContextService securityContextService;
    private final AddressService addressService;
    private final PetUtils petUtils;
    private final UserUtils userUtils;
    private final MapperUtils mapper;
    private final GlobalUtils globalUtils;

    public PetServiceImpl(PetRepositoryService petRepositoryService,
                          PetValidationService petValidationService,
                          SecurityContextService securityContextService,
                          AddressService addressService,
                          PetUtils petUtils,
                          UserUtils userUtils,
                          MapperUtils mapper,
                          GlobalUtils globalUtils) {
        this.petRepositoryService = petRepositoryService;
        this.petValidationService = petValidationService;
        this.securityContextService = securityContextService;
        this.addressService = addressService;
        this.petUtils = petUtils;
        this.userUtils = userUtils;
        this.mapper = mapper;
        this.globalUtils = globalUtils;
    }


    @Override
    @Transactional(
            readOnly = true
    )
    public ApiResponsePaginatedDto<PetMinDtoV2> listAll(Pageable pageable) {
        User user = securityContextService.getCurrentLoggedUser();
        Page<Pet> petPage = petRepositoryService.listAll(pageable);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        petUtils.markFavoritePets(user, petDtoList);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);

    }

    @Override
    public PetDtoV2 getById(Long id) {
        Pet pet = petRepositoryService.findById(id);
        petUtils.increasePetVisualization(pet);
        petRepositoryService.savePet(pet);

        PetDtoV2 petDto = mapper.parseObject(pet, PetDtoV2.class);

        petDto.setOwnerName(petUtils
                .preparePetOwnerName(pet.getUser().getFirstName(), pet.getUser().getLastName()));

        return petDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PetDetailsDto getPetDetails(Long id) {
        Pet pet = petRepositoryService.findById(id);

        Address address = pet.getAddress();
        City city = addressService.fetchCityByName(pet.getAddress().getCity());

        AddressResponseDto petAddress = petUtils
                .prepareAddressResponseDto(city, address);

        PetDetailsDto petDetails = mapper.parseObject(pet, PetDetailsDto.class);

        petDetails.setAddress(petAddress);

        return petDetails;
    }

    @Override
    @Transactional
    public ApiMessageResponseDto create(PetCreateRequestDto petDto) {
        petValidationService.validateCharacteristicsLimit(petDto.getCharacteristicsIds());

        Breed breed = petRepositoryService.fetchBreed(petDto.getBreedId(), petDto.getTypeId());
        List<PetCharacteristic> characteristicsList = petRepositoryService.fetchCharacteristics(petDto.getCharacteristicsIds());
        AnimalType animalType = petRepositoryService.fetchAnimalType(petDto.getTypeId());

        City city = addressService.fetchCityByIbge(petDto.getAddress().getCityIbge());
        Address address = addressService.buildAddress(petDto.getAddress(), city);

        User user = securityContextService.getCurrentLoggedUser();

        Pet pet = petUtils.buildPet(petDto, animalType, breed, characteristicsList, address, user);

        Pet savedPet = petRepositoryService.savePet(pet);

        petUtils.setPetPictures(savedPet, mapper.parseListObjects(petDto.getPetPictures(), PetPicture.class));

        petRepositoryService.savePet(pet);

        return new ApiMessageResponseDto("Pet criado com sucesso: " + pet.getName());
    }

    @Override
    @Transactional
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto petDto) {
        petValidationService.validateCharacteristicsLimit(petDto.getCharacteristicsIds());
        Pet pet = petRepositoryService.findById(id);

        Breed breed = petRepositoryService.fetchBreed(petDto.getBreedId(), petDto.getTypeId());
        List<PetCharacteristic> characteristicsList = petRepositoryService.fetchCharacteristics(petDto.getCharacteristicsIds());
        AnimalType animalType = petRepositoryService.fetchAnimalType(petDto.getTypeId());

        City city = addressService.fetchCityByIbge(petDto.getAddress().getCityIbge());
        Address address = addressService.buildAddress(petDto.getAddress(), city);

        petUtils.updatePet(pet, petDto, animalType, breed, characteristicsList, address);
        petUtils.setPetPictures(pet, mapper.parseListObjects(petDto.getPetPictures(), PetPicture.class));

        petRepositoryService.savePet(pet);

        return new ApiMessageResponseDto("Pet atualizado com sucesso");
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        Pet pet = petRepositoryService.findById(id);
        petUtils.setPetAsInactive(pet);
        petRepositoryService.savePet(pet);
        return new ApiMessageResponseDto("Pet deletado com sucesso");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponsePaginatedDto<PetMinDtoV2> getAllByUserId(Pageable pageable) {
        Long userId = securityContextService.getCurrentLoggedUser().getId();
        Page<Pet> petPage = petRepositoryService.fetchAllByUser(pageable, userId);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }


    @Override
    @Transactional(readOnly = true)
    public PetDto getBySerialNumber(String serialNumber) {
        Pet pet = petRepositoryService.findBySerialNumber(serialNumber);
        return mapper.parseObject(pet, PetDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PetDto> getUserSavedPets() {
        User user = securityContextService.getCurrentLoggedUser();
        List<UserSavedPets> userSavedPets = petRepositoryService.fetchAllUserSavedPets(user.getId());

        if (userSavedPets.isEmpty()) {
            return Collections.emptySet();
        }

        Set<Pet> pets = petUtils.extractPets(userSavedPets);

        return mapper.parseSetObjects(pets, PetDto.class);
    }


    @Override
    @Transactional
    public ApiMessageResponseDto addUserSavedPet(Long petId) {
        Pet pet = petRepositoryService.findById(petId);

        User user = securityContextService.getCurrentLoggedUser();

        if (petRepositoryService.existsByPetAndUser(petId, user.getId())) {
            throw new BadRequestException("O pet já foi salvo para o usuário informado");
        }

        petRepositoryService.saveUserSavedPet(new UserSavedPets(user, pet));

        return new ApiMessageResponseDto("Pet salvo com sucesso!");
    }

    @Override
    @Transactional
    public ApiMessageResponseDto removeUserSavedPet(Long petId) {
        User user = securityContextService.getCurrentLoggedUser();

        UserSavedPets userSavedPets = petRepositoryService.findSavedPetByPetAndUser(petId, user.getId());

        petRepositoryService.removeUserSavedPet(userSavedPets);

        return new ApiMessageResponseDto("Pet salvo removido com sucesso!");
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailsDto getPetOwnerDetails(Long petId) {
        Pet pet = petRepositoryService.findById(petId);
        User petOwner = pet.getUser();
        return userUtils.buildUserDetails(petOwner);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponsePaginatedDto<PetMinDtoV2> filterByAnimalTypes(Pageable pageable,
                                                                    List<Long> animalTypesIds) {
        User user = securityContextService.getCurrentLoggedUser();
        Page<Pet> petPage = petRepositoryService.findByAnimalTypes(pageable, animalTypesIds);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        petUtils.markFavoritePets(user, petDtoList);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponsePaginatedDto<PetMinDtoV2> filterByBreedOrAnimalType(Pageable pageable,
                                                                          Long breedId,
                                                                          Long animalTypeId) {
        if (!petUtils.breedIsPresent(breedId) && !petUtils.animalTypeIsPresent(animalTypeId)) {
            return globalUtils.buildApiResponsePaginated(Page.empty(), new ArrayList<>());
        }

        Page<Pet> petsPage = petUtils.filterPets(pageable, breedId, animalTypeId);

        return globalUtils.buildApiResponsePaginated(petsPage, mapper.parseListObjects(petsPage.getContent(), PetMinDtoV2.class));
    }
}
