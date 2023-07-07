package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.pet.AnimalTypeDto;

import java.util.List;

public interface AnimalTypeService {
    List<AnimalTypeDto> getAll();

    AnimalTypeDto getById(Long id);
}
