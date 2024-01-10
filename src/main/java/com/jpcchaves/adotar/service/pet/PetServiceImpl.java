package com.jpcchaves.adotar.service.pet;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetMinDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.service.address.contracts.AddressService;
import com.jpcchaves.adotar.service.pet.contracts.PetRepositoryService;
import com.jpcchaves.adotar.service.pet.contracts.PetService;
import com.jpcchaves.adotar.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.service.pet.contracts.PetValidationService;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepositoryService petRepositoryService;
    private final PetValidationService petValidationService;
    private final SecurityContextService securityContextService;
    private final AddressService addressService;
    private final PetUtils petUtils;
    private final MapperUtils mapper;
    private final GlobalUtils globalUtils;

    @Autowired
    public PetServiceImpl(PetRepositoryService petRepositoryService,
                          PetValidationService petValidationService,
                          SecurityContextService securityContextService,
                          AddressService addressService,
                          PetUtils petUtils,
                          MapperUtils mapper,
                          GlobalUtils globalUtils) {
        this.petRepositoryService = petRepositoryService;
        this.petValidationService = petValidationService;
        this.securityContextService = securityContextService;
        this.addressService = addressService;
        this.petUtils = petUtils;
        this.mapper = mapper;
        this.globalUtils = globalUtils;
    }


    @Override
    public ApiResponsePaginatedDto<PetMinDtoV2> listAll(Pageable pageable) {
        User user = securityContextService.getCurrentLoggedUser();
        Page<Pet> petPage = petRepositoryService.listAll(pageable);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        petUtils.markFavoritePets(user, petDtoList);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);

    }

    @Override
    public PetDto getById(Long id) {
        return mapper.parseObject(petRepositoryService.findById(id), PetDto.class);
    }

    @Override
    public ApiMessageResponseDto create(PetCreateRequestDto petDto) {
        petValidationService.validateCharacteristicsLimit(petDto.getCharacteristicsIds());
        Breed breed = petRepositoryService.fetchBreed(petDto.getBreedId(), petDto.getTypeId());
        List<PetCharacteristic> characteristicsList = petRepositoryService.fetchCharacteristics(petDto.getCharacteristicsIds());
        AnimalType animalType = petRepositoryService.fetchAnimalType(petDto.getTypeId());

        City city = addressService.fetchCityByIbge(petDto.getAddress().getCityIbge());
        Address address = addressService.createAddress(petDto.getAddress(), city);

        User user = securityContextService.getCurrentLoggedUser();

        Pet pet = petUtils.buildPetCreate(petDto, animalType, breed, characteristicsList, address, user);

        Pet savedPet = petRepositoryService.savePet(pet);

        petRepositoryService.createPetPictures(petDto.getPetPictures(), savedPet);

        return new ApiMessageResponseDto("Pet criado com sucesso: " + pet.getName());
    }

    @Override
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto petDto) {
        return null;
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        return null;
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDtoV2> getAllByUserId(Pageable pageable) {
        return null;
    }


    @Override
    public PetDto getBySerialNumber(String serialNumber) {
        return null;
    }

    @Override
    public Set<PetDto> getUserSavedPets() {
        return null;
    }

    @Override
    public UserDetailsDto getPetOwnerDetails(Long petId) {
        return null;
    }

    @Override
    public ApiMessageResponseDto addUserSavedPet(Long petId) {
        return null;
    }

    @Override
    public ApiMessageResponseDto removeUserSavedPet(Long petId) {
        return null;
    }


    @Override
    public ApiResponsePaginatedDto<PetMinDtoV2> filterByAnimalTypes(Pageable pageable,
                                                                    List<Long> animalTypesIds) {
        User user = securityContextService.getCurrentLoggedUser();
        Page<Pet> petPage = petRepositoryService.findByAnimalTypes(pageable, animalTypesIds);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        petUtils.markFavoritePets(user, petDtoList);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDto> filterByBreedOrAnimalType(Pageable pageable,
                                                                        Long breedId,
                                                                        Long animalTypeId) {
        return null;
    }
}
