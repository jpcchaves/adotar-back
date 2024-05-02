package com.jpcchaves.adotar.infrastructure.application.service.animaltype;

import com.jpcchaves.adotar.infrastructure.application.dto.pet.AnimalTypeMinDto;
import com.jpcchaves.adotar.infrastructure.application.service.animaltype.contracts.AnimalTypeService;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.model.AnimalType;
import com.jpcchaves.adotar.infrastructure.infra.repository.AnimalTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeServiceImpl implements AnimalTypeService {
  private final AnimalTypeRepository animalTypeRepository;
  private final MapperUtils mapperUtils;

  public AnimalTypeServiceImpl(
      AnimalTypeRepository animalTypeRepository, MapperUtils mapperUtils) {
    this.animalTypeRepository = animalTypeRepository;
    this.mapperUtils = mapperUtils;
  }

  @Override
  public List<AnimalTypeMinDto> getAll() {
    List<AnimalType> animalTypes = animalTypeRepository.findAll();
    return mapperUtils.parseListObjects(animalTypes, AnimalTypeMinDto.class);
  }

  @Override
  public AnimalTypeMinDto getById(Long id) {
    AnimalType animalType =
        animalTypeRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Tipo de animal não encontrado com o id: " + id));
    return mapperUtils.parseObject(animalType, AnimalTypeMinDto.class);
  }
}
