package com.jpcchaves.adotar.service.pet;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureCreateDto;
import com.jpcchaves.adotar.repository.PetPictureRepository;
import com.jpcchaves.adotar.repository.PetRepository;
import com.jpcchaves.adotar.repository.UserSavedPetsRepository;
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
    private final PetPictureRepository petPictureRepository;
    private final MapperUtils mapper;

    public PetRepositoryServiceImpl(PetRepository petRepository,
                                    UserSavedPetsRepository userSavedPetsRepository,
                                    PetPictureRepository petPictureRepository,
                                    MapperUtils mapper) {
        this.petRepository = petRepository;
        this.userSavedPetsRepository = userSavedPetsRepository;
        this.petPictureRepository = petPictureRepository;
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
        return null;
    }

    @Override
    public List<PetCharacteristic> fetchCharacteristics(List<Long> characteristicsIds) {
        return null;
    }

    @Override
    public AnimalType fetchAnimalType(Long animalTypeId) {
        return null;
    }

    @Override
    public void createPetPictures(List<PetPictureCreateDto> petPicturesDto,
                                  Pet pet) {
        List<PetPicture> petPictures = mapper.parseListObjects(petPicturesDto, PetPicture.class);

        for (PetPicture petPicture : petPictures) {
            petPicture.setPet(pet);
        }

        petPictureRepository.saveAll(petPictures);
    }

    @Override
    public Page<Pet> fetchAllByUser(Pageable pageable,
                                    Long userId) {
        return petRepository.getAllByUser_Id(pageable, userId);
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
}
