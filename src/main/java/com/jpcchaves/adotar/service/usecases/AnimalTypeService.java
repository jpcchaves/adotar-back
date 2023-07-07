package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.pet.AnimalTypeMinDto;

import java.util.List;

public interface AnimalTypeService {
    List<AnimalTypeMinDto> getAll();

    AnimalTypeMinDto getById(Long id);
}
