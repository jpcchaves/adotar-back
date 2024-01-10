package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.*;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.repository.*;
import com.jpcchaves.adotar.service.usecases.v1.PetService;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.utils.pet.PetUtils;
import com.jpcchaves.adotar.utils.user.UserUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetCharacteristicRepository petCharacteristicRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final CityRepository cityRepository;
    private final PetPictureRepository petPictureRepository;
    private final SecurityContextService securityContextService;
    private final UserSavedPetsRepository userSavedPetsRepository;
    private final GlobalUtils globalUtils;
    private final UserUtils userUtils;
    private final MapperUtils mapper;

    public PetServiceImpl(PetRepository petRepository,
                          PetCharacteristicRepository petCharacteristicRepository,
                          AnimalTypeRepository animalTypeRepository,
                          BreedRepository breedRepository,
                          CityRepository cityRepository,
                          PetPictureRepository petPictureRepository,
                          SecurityContextService securityContextService,
                          UserSavedPetsRepository userSavedPetsRepository,
                          GlobalUtils globalUtils,
                          UserUtils userUtils,
                          MapperUtils mapper) {
        this.petRepository = petRepository;
        this.petCharacteristicRepository = petCharacteristicRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.cityRepository = cityRepository;
        this.petPictureRepository = petPictureRepository;
        this.securityContextService = securityContextService;
        this.userUtils = userUtils;
        this.userSavedPetsRepository = userSavedPetsRepository;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDto> listAll(Pageable pageable) {
        Page<Pet> petsPage = petRepository.findAllByActiveTrue(pageable);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    @Override
    public PetDto getById(Long id) {
        Pet pet = fetchPetById(id);

        PetUtils.increasePetVisualization(pet);
        savePet(pet);

        return mapper.parseObject(pet, PetDto.class);
    }

    @Override
    public PetDto getBySerialNumber(String serialNumber) {
        Pet pet = petRepository
                .findBySerialNumberAndActiveTrue(serialNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o número de série informado"));

        return mapper.parseObject(pet, PetDto.class);
    }

    @Override
    @Transactional
    public ApiMessageResponseDto create(PetCreateRequestDto requestDto) {
        validateCharacteristicsLimit(requestDto.getCharacteristicsIds());

        Breed breed = fetchBreed(requestDto.getBreedId(), requestDto.getTypeId());

        List<PetCharacteristic> characteristicsList = fetchCharacteristics(requestDto.getCharacteristicsIds());

        AnimalType animalType = fetchAnimalType(requestDto.getTypeId());

        City city = fetchCity(requestDto.getCityIbge());
        Address address = createAddress(requestDto, city);


        Pet pet = buildPet(requestDto, animalType, breed, characteristicsList, address);

        Pet savedPet = savePet(pet);

        createPetPictures(requestDto.getPetPictures(), savedPet);

        return new ApiMessageResponseDto("Pet criado com sucesso: " + pet.getName());
    }

    @Override
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto requestDto) {
        validateCharacteristicsLimit(requestDto.getCharacteristicsIds());

        Pet pet = getPetById(id);
        Breed breed = fetchBreed(requestDto.getBreedId(), requestDto.getTypeId());
        List<PetCharacteristic> characteristicsList = fetchCharacteristics(requestDto.getCharacteristicsIds());
        AnimalType animalType = fetchAnimalType(requestDto.getTypeId());
        PetUtils.updatePetAttributes(pet, requestDto);

        definePetType(pet, animalType);
        definePetBreed(pet, breed);
        definePetCharacteristics(pet, characteristicsList);

        savePet(pet);

        return new ApiMessageResponseDto("Pet atualizado com sucesso");
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        Pet pet = getPetById(id);

        inactivatePet(pet);

        savePet(pet);

        return new ApiMessageResponseDto("Pet deletado com sucesso");
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDtoV2> getAllByUserId(Pageable pageable) {
        Long userId = securityContextService.getCurrentLoggedUser().getId();
        Page<Pet> petPage = petRepository.getAllByUser_Id(pageable, userId);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }

    @Override
    public Set<PetDto> getUserSavedPets() {
        User user = securityContextService.getCurrentLoggedUser();
        List<UserSavedPets> userSavedPets = userSavedPetsRepository.findAllByUserId(user.getId());

        if (isSavedPetsEmpty(userSavedPets)) {
            return buildEmptyList();
        }

        Set<Pet> savedPets = extractPets(userSavedPets);

        return mapper.parseSetObjects(savedPets, PetDto.class);
    }

    @Override
    public ApiMessageResponseDto addUserSavedPet(Long petId) {
        Pet pet = getPetById(petId);

        User user = securityContextService.getCurrentLoggedUser();

        if (existsByPetAndUser(petId, user.getId())) {
            throw new BadRequestException("O pet já foi salvo para o usuário informado");
        }

        userSavedPetsRepository.save(new UserSavedPets(user, pet));

        return new ApiMessageResponseDto("Pet salvo com sucesso!");
    }

    @Override
    public ApiMessageResponseDto removeUserSavedPet(Long petId) {
        User user = securityContextService.getCurrentLoggedUser();

        UserSavedPets userSavedPets = findByUserAndPet(user, petId);

        userSavedPetsRepository.delete(userSavedPets);

        return new ApiMessageResponseDto("Pet salvo removido com sucesso!");
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDto> filterByBreedOrAnimalType(Pageable pageable,
                                                                        Long breedId,
                                                                        Long animalTypeId) {
        if (breedAndAnimalTypeIsPresent(breedId, animalTypeId)) {
            return doFilterByBreedAndAnimalType(pageable, breedId, animalTypeId);
        }

        if (animalTypeIsPresent(animalTypeId)) {
            return doFilterByAnimalType(pageable, animalTypeId);
        }

        if (breedIsPresent(breedId)) {
            return doFilterByBreed(pageable, breedId);
        }

        return listAll(pageable);
    }

    @Override
    public UserDetailsDto getPetOwnerDetails(Long petId) {
        Pet pet = getPetById(petId);
        User petOwner = pet.getUser();

        return userUtils.buildUserDetails(petOwner);
    }

    private Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    private void definePetCharacteristics(Pet pet,
                                          List<PetCharacteristic> characteristicList) {
        pet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicList));
    }

    private void definePetBreed(Pet pet,
                                Breed breed) {
        pet.setBreed(breed);
    }

    private void definePetType(Pet pet,
                               AnimalType animalType) {
        pet.setType(animalType);

    }

    private Pet fetchPetById(Long id) {
        return petRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + id));
    }

    private void validateCharacteristicsLimit(List<Long> characteristicsIds) {
        PetUtils.verifyCharacteristicsLimit(characteristicsIds);
    }

    private Breed fetchBreed(Long breedId,
                             Long typeId) {
        return getBreedByIdAndAnimalType(breedId, typeId);
    }

    private List<PetCharacteristic> fetchCharacteristics(List<Long> characteristicIds) {
        return petCharacteristicRepository.findAllById(characteristicIds);
    }

    private AnimalType fetchAnimalType(Long typeId) {
        return animalTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de animal não encontrado!"));
    }

    private City fetchCity(Long cityIbge) {
        return cityRepository.findCityByIbge(cityIbge)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
    }

    private Address createAddress(PetCreateRequestDto requestDto,
                                  City city) {
        return buildAddress(requestDto, city);
    }

    private Pet buildPet(PetCreateRequestDto requestDto,
                         AnimalType animalType,
                         Breed breed,
                         List<PetCharacteristic> characteristicsList,
                         Address address) {
        Pet pet = PetUtils.buildPetCreate(requestDto, animalType, breed, characteristicsList, address);
        pet.setUser(securityContextService.getCurrentLoggedUser());
        return pet;
    }

    private UserSavedPets findByUserAndPet(User user,
                                           Long petId) {
        return userSavedPetsRepository
                .findByPet_IdAndUser_Id(petId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("O usuário não possui o pet salvo"));
    }

    private void inactivatePet(Pet pet) {
        pet.setActive(false);
        pet.setDeletedAt(new Date());
    }

    private boolean isSavedPetsEmpty(List<UserSavedPets> userSavedPets) {
        return userSavedPets.isEmpty();
    }

    private Address buildAddress(PetCreateRequestDto petCreateRequestDto,
                                 City city) {
        return new Address(
                petCreateRequestDto.getZipcode(),
                petCreateRequestDto.getStreet(),
                petCreateRequestDto.getNumber(),
                petCreateRequestDto.getComplement(),
                petCreateRequestDto.getNeighborhood(),
                city.getName(),
                city.getState().getName()
        );
    }

    private Pet getPetById(Long petId) {
        return petRepository
                .findByIdAndActiveTrue(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + petId));
    }

    private boolean existsByPetAndUser(Long petId,
                                       Long userId) {
        return userSavedPetsRepository.existsByPet_IdAndUser_Id(petId, userId);
    }

    private <T> Set<T> buildEmptyList() {
        return new HashSet<>();
    }

    private Breed getBreedByIdAndAnimalType(Long breedId,
                                            Long typeId) {
        return breedRepository
                .findByIdAndAnimalType_Id(breedId, typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada!"));
    }

    private boolean breedAndAnimalTypeIsPresent(Long breedId,
                                                Long animalTypeId) {
        return breedId != null && animalTypeId != null;
    }

    private boolean animalTypeIsPresent(Long animalTypeId) {
        return animalTypeId != null;
    }

    private boolean breedIsPresent(Long breedId) {
        return breedId != null;
    }

    private ApiResponsePaginatedDto<PetMinDto> doFilterByBreedAndAnimalType(Pageable pageable,
                                                                            Long breedId,
                                                                            Long animalTypeId) {
        Page<Pet> petsPage = petRepository
                .getAllByBreed_IdAndType_Id(pageable, breedId, animalTypeId);

        List<PetMinDto> petDtoList = mapper
                .parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    private ApiResponsePaginatedDto<PetMinDto> doFilterByAnimalType(Pageable pageable,
                                                                    Long animalTypeId) {
        Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, animalTypeId);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    private ApiResponsePaginatedDto<PetMinDto> doFilterByBreed(Pageable pageable,
                                                               Long breedId) {
        Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, breedId);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    private Set<Pet> extractPets(List<UserSavedPets> userSavedPets) {
        List<Pet> pets = new ArrayList<>();
        for (UserSavedPets userSavedPet : userSavedPets) {
            pets.add(userSavedPet.getPet());
        }

        return new HashSet<>(pets);
    }

    private void createPetPictures(List<PetPictureCreateDto> petPicturesDtos,
                                   Pet pet) {
        List<PetPicture> petPictures = mapper.parseListObjects(petPicturesDtos, PetPicture.class);

        for (PetPicture petPicture : petPictures) {
            petPicture.setPet(pet);
        }

        petPictureRepository.saveAll(petPictures);
    }
}
