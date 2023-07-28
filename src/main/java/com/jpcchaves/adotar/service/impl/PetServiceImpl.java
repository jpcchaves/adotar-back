package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.*;
import com.jpcchaves.adotar.repository.*;
import com.jpcchaves.adotar.service.usecases.PetService;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.utils.pet.PetUtils;
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
    private final PetPictureRepository petPictureRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final SecurityContextService securityContextService;
    private final UserRepository userRepository;
    private final UserSavedPetsRepository userSavedPetsRepository;
    private final GlobalUtils globalUtils;
    private final MapperUtils mapper;

    public PetServiceImpl(PetRepository petRepository,
                          PetCharacteristicRepository petCharacteristicRepository,
                          AnimalTypeRepository animalTypeRepository,
                          BreedRepository breedRepository,
                          PetPictureRepository petPictureRepository,
                          AddressRepository addressRepository,
                          CityRepository cityRepository,
                          SecurityContextService securityContextService,
                          UserRepository userRepository,
                          UserSavedPetsRepository userSavedPetsRepository,
                          GlobalUtils globalUtils,
                          MapperUtils mapper) {
        this.petRepository = petRepository;
        this.petCharacteristicRepository = petCharacteristicRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.petPictureRepository = petPictureRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.securityContextService = securityContextService;
        this.userRepository = userRepository;
        this.userSavedPetsRepository = userSavedPetsRepository;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDto> listAll(Pageable pageable) {
        Page<Pet> petsPage = petRepository.findAllByActive(pageable, true);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    @Override
    public PetDto getById(Long id) {
        Pet pet = petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o id informado: " + id));

        PetUtils.increasePetVisualization(pet);
        petRepository.save(pet);

        return mapper.parseObject(pet, PetDto.class);
    }
    
    @Override
    public ApiMessageResponseDto create(PetCreateRequestDto petCreateRequestDto) {
        Breed breed = breedRepository
                .findByIdAndAnimalType_Id(petCreateRequestDto.getBreedId(), petCreateRequestDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada!"));

        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petCreateRequestDto.getCharacteristicsIds());

        AnimalType animalType = animalTypeRepository
                .findById(petCreateRequestDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de animal não encotrado!"));

        City city = cityRepository
                .findById(petCreateRequestDto.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));

        Address address = addressRepository.save(buildAddress(petCreateRequestDto, city));

        List<PetPicture> petPictures = mapper.parseListObjects(petCreateRequestDto.getPetPictures(), PetPicture.class);

        Pet pet = PetUtils.buildPetCreate(petCreateRequestDto, animalType, breed, characteristicsList, address);

        Pet savedPet = petRepository.save(pet);

        pet.setUser(securityContextService.getCurrentLoggedUser());

        for (PetPicture picture : petPictures) {
            picture.setPet(savedPet);
        }

        if (pictureExists(petCreateRequestDto.getPetPictures())) {
            petPictureRepository.saveAll(petPictures);
        }

        return new ApiMessageResponseDto("Pet criado com sucesso: " + petCreateRequestDto.getName());
    }


    @Override
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto petDto) {
        Pet pet = petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o id informado: " + id));

        Breed breed = breedRepository
                .findByIdAndAnimalType_Id(petDto.getBreedId(), petDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada!"));

        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petDto.getCharacteristicsIds());

        AnimalType animalType = animalTypeRepository
                .findById(petDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("O tipo de animal informado não existe"));

        Pet updatedPet = PetUtils.updatePetAttributes(pet, petDto);

        updatedPet.setBreed(breed);
        updatedPet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicsList));
        updatedPet.setType(animalType);

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet atualizado com sucesso");
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o id informado: " + id));

        inactivatePet(pet);

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet deletado com sucesso");
    }

    @Override
    public ApiResponsePaginatedDto<PetDto> getAllByUser_Id(Pageable pageable) {
        Page<Pet> petPage = petRepository.getAllByUser_Id(pageable, securityContextService.getCurrentLoggedUser().getId());
        List<PetDto> petDtoList = mapper.parseListObjects(petPage.getContent(), PetDto.class);
        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }

    @Override
    public Set<PetDto> getUserSavedPets() {
        User user = securityContextService.getCurrentLoggedUser();
        List<UserSavedPets> userSavedPets = userSavedPetsRepository.findAllByUserId(user.getId());

        if (isSavedPetsEmpty(userSavedPets)) {
            return new HashSet<>();
        }

        List<Pet> pets = new ArrayList<>();
        for (UserSavedPets userSavedPet : userSavedPets) {
            pets.add(userSavedPet.getPet());
        }
        Set<Pet> petsSet = new HashSet<>(pets);

        return mapper.parseSetObjects(petsSet, PetDto.class);
    }

    @Override
    public ApiMessageResponseDto addUserSavedPet(Long petId) {
        Pet pet = petRepository
                .findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + petId));

        User user = securityContextService.getCurrentLoggedUser();

        if (userSavedPetsRepository.existsByPet_IdAndUser_Id(petId, user.getId())) {
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
    public ApiResponsePaginatedDto<PetMinDto> getAllByBreed(Pageable pageable,
                                                            Long breedId,
                                                            Long animalTypeId) {


        if (breedId != null && animalTypeId != null) {
            Page<Pet> petsPage = petRepository.getAllByBreed_IdAndType_Id(pageable, breedId, animalTypeId);
            List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

            return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
        }

        if (animalTypeId != null) {
            Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, animalTypeId);
            List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

            return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
        }

        if (breedId != null) {
            Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, breedId);
            List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

            return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
        }

        return listAll(pageable);
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

    private boolean pictureExists(List<PetPictureDto> petPictures) {
        return petPictures.size() > 0;
    }
}
