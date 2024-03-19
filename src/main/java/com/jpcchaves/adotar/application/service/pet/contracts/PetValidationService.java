package com.jpcchaves.adotar.application.service.pet.contracts;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PetValidationService {
  void validateCharacteristicsLimit(List<Long> characteristicsIds);

  void validateMultipartFilePetPictures(List<MultipartFile> petPictures);

  void validateEncodedPetPictures(List<String> petPictures);
}
