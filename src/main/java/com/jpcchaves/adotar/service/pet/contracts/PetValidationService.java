package com.jpcchaves.adotar.service.pet.contracts;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetValidationService {
    void validateCharacteristicsLimit(List<Long> characteristicsIds);
    void validatePetPictures(List<MultipartFile> petPictures);

}
