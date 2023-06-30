package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
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

import java.util.Date;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetCharacteristicRepository petCharacteristicRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final PetPictureRepository petPictureRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;
    private final GlobalUtils globalUtils;
    private final MapperUtils mapper;

    public PetServiceImpl(PetRepository petRepository,
                          PetCharacteristicRepository petCharacteristicRepository,
                          AnimalTypeRepository animalTypeRepository,
                          BreedRepository breedRepository,
                          PetPictureRepository petPictureRepository,
                          GlobalUtils globalUtils,
                          MapperUtils mapper,
                          SecurityContextService securityContextService,
                          UserRepository userRepository) {
        this.petRepository = petRepository;
        this.petCharacteristicRepository = petCharacteristicRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.petPictureRepository = petPictureRepository;
        this.userRepository = userRepository;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
        this.securityContextService = securityContextService;
    }

    @Override
    public ApiResponsePaginatedDto<PetDto> listAll(Pageable pageable) {
        Page<Pet> petsPage = petRepository.findAll(pageable);
        List<PetDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    @Override
    public PetDto getById(Long id) {
        Pet pet = petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find pet with id: " + id));

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

        List<PetPicture> petPictures = mapper.parseListObjects(petCreateRequestDto.getPetPictures(), PetPicture.class);

        Pet pet = PetUtils.buildPetCreate(petCreateRequestDto, animalType, breed, characteristicsList);

        Pet savedPet = petRepository.save(pet);

        pet.setUser(securityContextService.getCurrentLoggedUser());

        for (PetPicture picture : petPictures) {
            picture.setPet(savedPet);
        }

        if (petCreateRequestDto.getPetPictures().size() > 0) {
            petPictureRepository.saveAll(petPictures);
        }


        return new ApiMessageResponseDto("Successfully created pet: " + petCreateRequestDto.getName());
    }

    @Override
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto petDto) {
        Pet pet = petRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find pet with id: " + id));

        Breed breed = breedRepository
                .findByIdAndAnimalType_Id(petDto.getBreedId(), petDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada!"));

        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petDto.getCharacteristicsIds());

        AnimalType animalType = animalTypeRepository
                .findById(petDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal type not found!"));

        Pet updatedPet = PetUtils.updatePetAttributes(pet, petDto);

        updatedPet.setBreed(breed);
        updatedPet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicsList));
        updatedPet.setType(animalType);

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet atualizado com sucesso");
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + id));

        pet.setActive(false);
        pet.setDeletedAt(new Date());

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
    public ApiResponsePaginatedDto<PetDto> getAllByBreed(Pageable pageable,
                                                         Long breedId,
                                                         Long animalTypeId) {


        if (breedId != null && animalTypeId != null) {
            Page<Pet> petsPage = petRepository.getAllByBreed_IdAndType_Id(pageable, breedId, animalTypeId);
            List<PetDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetDto.class);

            return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
        }

        if (animalTypeId != null) {
            Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, animalTypeId);
            List<PetDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetDto.class);

            return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
        }

        if (breedId != null) {
            Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, breedId);
            List<PetDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetDto.class);

            return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
        }

        return listAll(pageable);
    }
}
