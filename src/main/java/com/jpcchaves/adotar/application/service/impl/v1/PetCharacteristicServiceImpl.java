package com.jpcchaves.adotar.application.service.impl.v1;

import com.jpcchaves.adotar.domain.model.PetCharacteristic;
import com.jpcchaves.adotar.application.dto.pet.PetCharacteristicsDto;
import com.jpcchaves.adotar.infra.repository.PetCharacteristicRepository;
import com.jpcchaves.adotar.application.service.usecases.v1.PetCharacteristicService;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCharacteristicServiceImpl implements PetCharacteristicService {
    private final MapperUtils mapperUtils;
    private final PetCharacteristicRepository petCharacteristicRepository;

    public PetCharacteristicServiceImpl(MapperUtils mapperUtils,
                                        PetCharacteristicRepository petCharacteristicRepository) {
        this.mapperUtils = mapperUtils;
        this.petCharacteristicRepository = petCharacteristicRepository;
    }

    @Override
    public List<PetCharacteristicsDto> getAll() {
        List<PetCharacteristic> petCharacteristics = petCharacteristicRepository.findAll();
        return mapperUtils.parseListObjects(petCharacteristics, PetCharacteristicsDto.class);
    }
}