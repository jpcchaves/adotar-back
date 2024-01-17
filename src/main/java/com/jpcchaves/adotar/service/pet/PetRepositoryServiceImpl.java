package com.jpcchaves.adotar.service.pet;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.repository.*;
import com.jpcchaves.adotar.service.pet.contracts.PetRepositoryService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetRepositoryServiceImpl implements PetRepositoryService {
    private final PetRepository petRepository;
    private final UserSavedPetsRepository userSavedPetsRepository;
    private final PetCharacteristicRepository petCharacteristicRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final MapperUtils mapper;

    public PetRepositoryServiceImpl(PetRepository petRepository,
                                    UserSavedPetsRepository userSavedPetsRepository,
                                    PetCharacteristicRepository petCharacteristicRepository,
                                    AnimalTypeRepository animalTypeRepository,
                                    BreedRepository breedRepository,
                                    MapperUtils mapper) {
        this.petRepository = petRepository;
        this.userSavedPetsRepository = userSavedPetsRepository;
        this.petCharacteristicRepository = petCharacteristicRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<Pet> listAll(Pageable pageable) {
        return petRepository.findAllByActiveTrue(pageable);
    }

    @Override
    public boolean isPetSavedByUser(Long userId,
                                    Long petId) {
        return userSavedPetsRepository.existsByPet_IdAndUser_Id(petId, userId);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + id));
    }

    @Override
    public Page<Pet> findByAnimalTypes(Pageable pageable,
                                       List<Long> animalTypesIds) {
        return petRepository.findByTypes(pageable, animalTypesIds);
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Breed fetchBreed(Long breedId,
                            Long animalTypeId) {
        return breedRepository
                .findByIdAndAnimalType_Id(breedId, animalTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada!"));
    }

    @Override
    public List<PetCharacteristic> fetchCharacteristics(List<Long> characteristicsIds) {
        return petCharacteristicRepository.findAllById(characteristicsIds);
    }

    @Override
    public AnimalType fetchAnimalType(Long animalTypeId) {
        return animalTypeRepository
                .findById(animalTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo não encontrado!"));
    }

    @Override
    public Page<Pet> fetchAllByUser(Pageable pageable,
                                    Long userId) {
        return petRepository.getAllByUser_IdAndActiveTrue(pageable, userId);
    }

    @Override
    public Pet findBySerialNumber(String serialNumber) {
        return petRepository
                .findBySerialNumberAndActiveTrue(serialNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o número de série informado"));

    }

    @Override
    public List<UserSavedPets> fetchAllUserSavedPets(Long userId) {
        return userSavedPetsRepository.findAllByUserId(userId);
    }

    @Override
    public boolean existsByPetAndUser(Long petId,
                                      Long userId) {
        return userSavedPetsRepository.existsByPet_IdAndUser_Id(petId, userId);
    }

    @Override
    public UserSavedPets findSavedPetByPetAndUser(Long petId,
                                                  Long userId) {
        return userSavedPetsRepository.findByPet_IdAndUser_Id(petId, userId).orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o id informado " + petId));
    }

    @Override
    public UserSavedPets saveUserSavedPet(UserSavedPets userSavedPet) {
        return userSavedPetsRepository.save(userSavedPet);
    }

    @Override
    public void removeUserSavedPet(UserSavedPets userSavedPets) {
        userSavedPetsRepository.delete(userSavedPets);
    }

    @Override
    public Page<Pet> getAllByBreedIdAndTypeId(Pageable pageable,
                                              Long breedId,
                                              Long typeId) {
        return petRepository.getAllByBreed_IdAndType_Id(pageable, breedId, typeId);
    }

    @Override
    public Page<Pet> getAllByTypeId(Pageable pageable,
                                    Long typeId) {
        return petRepository.getAllByType_Id(pageable, typeId);
    }

    @Override
    public Page<Pet> getAllByBreedId(Pageable pageable,
                                     Long breedId) {
        return petRepository.getAllByBreed_Id(pageable, breedId);
    }
}
