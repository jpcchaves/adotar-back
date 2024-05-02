package com.jpcchaves.adotar.infrastructure.application.service.breed;

import com.jpcchaves.adotar.infrastructure.application.dto.pet.BreedDto;
import com.jpcchaves.adotar.infrastructure.application.service.breed.contracts.BreedService;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.model.Breed;
import com.jpcchaves.adotar.infrastructure.infra.repository.BreedRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BreedServiceImpl implements BreedService {

  private final BreedRepository breedRepository;
  private final MapperUtils mapper;

  public BreedServiceImpl(BreedRepository breedRepository, MapperUtils mapper) {
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
