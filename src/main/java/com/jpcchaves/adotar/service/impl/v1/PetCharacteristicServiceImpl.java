package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
import com.jpcchaves.adotar.payload.dto.pet.PetCharacteristicsDto;
import com.jpcchaves.adotar.repository.PetCharacteristicRepository;
import com.jpcchaves.adotar.service.usecases.v1.PetCharacteristicService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
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
