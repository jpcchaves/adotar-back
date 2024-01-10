package com.jpcchaves.adotar.service.pet;

import com.jpcchaves.adotar.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.service.pet.contracts.PetValidationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetValidationServiceImpl implements PetValidationService {
    private final PetUtils petUtils;

    public PetValidationServiceImpl(PetUtils petUtils) {
        this.petUtils = petUtils;
    }

    @Override
    public void validateCharacteristicsLimit(List<Long> characteristicsIds) {
        petUtils.verifyCharacteristicsLimit(characteristicsIds);
    }
}
