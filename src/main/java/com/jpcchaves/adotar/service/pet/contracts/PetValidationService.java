package com.jpcchaves.adotar.service.pet.contracts;

import java.util.List;

public interface PetValidationService {
    void validateCharacteristicsLimit(List<Long> characteristicsIds);

}
