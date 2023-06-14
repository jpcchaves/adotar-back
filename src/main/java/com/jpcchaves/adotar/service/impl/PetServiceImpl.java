package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.AnimalType;
import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.repository.AnimalTypeRepository;
import com.jpcchaves.adotar.repository.PetCharacteristicRepository;
import com.jpcchaves.adotar.repository.PetRepository;
import com.jpcchaves.adotar.service.usecases.PetService;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
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
    private final CollectionsUtils collectionUtils;
    private final GlobalUtils globalUtils;
    private final MapperUtils mapper;

    public PetServiceImpl(PetRepository petRepository,
                          PetCharacteristicRepository petCharacteristicRepository,
                          AnimalTypeRepository animalTypeRepository,
                          CollectionsUtils collectionUtils,
                          GlobalUtils globalUtils,
                          MapperUtils mapper) {
        this.petRepository = petRepository;
        this.petCharacteristicRepository = petCharacteristicRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.collectionUtils = collectionUtils;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
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
                .orElseThrow(() -> new RuntimeException("Could not find pet with id: " + id));

        increasePetVisualization(pet);
        petRepository.save(pet);

        return mapper.parseObject(pet, PetDto.class);
    }

    @Override
    public ApiMessageResponseDto create(PetCreateRequestDto petCreateRequestDto) {
        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petCreateRequestDto.getCharacteristicsIds());

        AnimalType animalType = animalTypeRepository
                .findById(petCreateRequestDto.getTypeId())
                .orElseThrow(() -> new RuntimeException("Animal type not found!"));

        Pet pet = buildPetCreate(petCreateRequestDto, animalType, characteristicsList);

        petRepository.save(pet);

        return new ApiMessageResponseDto("Successfully created pet: " + petCreateRequestDto.getName());
    }

    @Override
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto petDto) {
        Pet pet = petRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find pet with id: " + id));

        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petDto.getCharacteristics());

        AnimalType animalType = animalTypeRepository
                .findById(petDto.getTypeId())
                .orElseThrow(() -> new RuntimeException("Animal type not found!"));

        pet.setId(pet.getId());
        pet.setName(petDto.getName());
        pet.setYearsAge(petDto.getYearsAge());
        pet.setMonthsAge(petDto.getMonthsAge());
        pet.setGender(petDto.getGender());
        pet.setSize(petDto.getSize());
        pet.setHealthCondition(petDto.getHealthCondition());
        pet.setColor(petDto.getColor());
        pet.setDescription(petDto.getDescription());
        pet.setVisualizations(petDto.getVisualizations());
        pet.setAvailable(petDto.isAvailable());
        pet.setAdoptionDate(petDto.getAdoptionDate());
        pet.setCharacteristics(collectionUtils.convertListToSet(characteristicsList));
        pet.setType(animalType);

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet atualizado com sucesso");
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found with id " + id));

        pet.setActive(false);
        pet.setDeletedAt(new Date());

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet deletado com sucesso");
    }

    private Pet buildPetCreate(PetCreateRequestDto petCreateRequestDto,
                               AnimalType animalType,
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
        pet.setCharacteristics(collectionUtils.convertListToSet(characteristicsList));

        return pet;
    }

    private void increasePetVisualization(Pet pet) {
        int ONE = 1;
        pet.setVisualizations(pet.getVisualizations() + ONE);
    }
}
