package com.jpcchaves.adotar.infrastructure.application.service.pet;

import com.jpcchaves.adotar.infrastructure.application.dto.pet.PetCharacteristicsDto;
import com.jpcchaves.adotar.infrastructure.application.service.pet.contracts.PetCharacteristicService;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.model.PetCharacteristic;
import com.jpcchaves.adotar.infrastructure.infra.repository.PetCharacteristicRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PetCharacteristicServiceImpl implements PetCharacteristicService {
  private final MapperUtils mapperUtils;
  private final PetCharacteristicRepository petCharacteristicRepository;

  public PetCharacteristicServiceImpl(
      MapperUtils mapperUtils,
      PetCharacteristicRepository petCharacteristicRepository) {
    this.mapperUtils = mapperUtils;
    this.petCharacteristicRepository = petCharacteristicRepository;
  }

  @Override
  public List<PetCharacteristicsDto> getAll() {
    List<PetCharacteristic> petCharacteristics =
        petCharacteristicRepository.findAll();
    return mapperUtils.parseListObjects(
        petCharacteristics, PetCharacteristicsDto.class);
  }
}
