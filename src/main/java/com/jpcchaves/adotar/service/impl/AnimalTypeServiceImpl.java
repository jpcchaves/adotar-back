package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.AnimalType;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.pet.AnimalTypeMinDto;
import com.jpcchaves.adotar.repository.AnimalTypeRepository;
import com.jpcchaves.adotar.service.usecases.AnimalTypeService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalTypeServiceImpl implements AnimalTypeService {
    private final AnimalTypeRepository animalTypeRepository;
    private final MapperUtils mapperUtils;

    public AnimalTypeServiceImpl(AnimalTypeRepository animalTypeRepository,
                                 MapperUtils mapperUtils) {
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
        AnimalType animalType = animalTypeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de animal não encontrado com o id: " + id));
        return mapperUtils.parseObject(animalType, AnimalTypeMinDto.class);
    }
}
