package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.repository.PetRepository;
import com.jpcchaves.adotar.service.usecases.PetService;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {
    private final int ONE = 1;

    private final PetRepository petRepository;
    private final CollectionsUtils collectionUtils;
    private final GlobalUtils globalUtils;
    private final MapperUtils mapper;

    public PetServiceImpl(PetRepository petRepository,
                          CollectionsUtils collectionUtils,
                          GlobalUtils globalUtils,
                          MapperUtils mapper) {
        this.petRepository = petRepository;
        this.collectionUtils = collectionUtils;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
    }

    @Override
    public ApiResponsePaginatedDto<PetDto> listAll(Pageable pageable) {
        Page<Pet> petsPage = petRepository.findAll(pageable);

        Page<PetDto> petDtoPage = globalUtils.buildPage(petsPage, pageable, PetDto.class);

        return globalUtils.buildApiResponsePaginated(petDtoPage);
    }

    @Override
    public PetDto getById(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find pet with id: " + id));

        increasePetVisualization(pet);
        petRepository.save(pet);

        return mapper.parseObject(pet, PetDto.class);
    }

    private void increasePetVisualization(Pet pet) {
        pet.setVisualizations(pet.getVisualizations() + ONE);
    }
}
