package com.jpcchaves.adotar.application.service.impl.v1;

import com.jpcchaves.adotar.application.dto.pet.BreedDto;
import com.jpcchaves.adotar.application.service.usecases.BreedService;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.domain.model.Breed;
import com.jpcchaves.adotar.infra.repository.BreedRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;
    private final MapperUtils mapper;

    public BreedServiceImpl(BreedRepository breedRepository,
                            MapperUtils mapper) {
        this.breedRepository = breedRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BreedDto> findAllByAnimalType(Long animalTypeId) {
        List<Breed> breedList =
                breedRepository.findAllByAnimalType_Id(animalTypeId);
        return mapper.parseListObjects(breedList, BreedDto.class);
    }
}
