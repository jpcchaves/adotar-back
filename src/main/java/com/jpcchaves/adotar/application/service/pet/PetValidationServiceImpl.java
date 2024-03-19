package com.jpcchaves.adotar.application.service.pet;

import com.jpcchaves.adotar.application.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.application.service.pet.contracts.PetValidationService;
import com.jpcchaves.adotar.application.utils.files.contracts.FileUtils;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetValidationServiceImpl implements PetValidationService {
  private final PetUtils petUtils;
  private final FileUtils fileUtils;

  public PetValidationServiceImpl(PetUtils petUtils, FileUtils fileUtils) {
    this.petUtils = petUtils;
    this.fileUtils = fileUtils;
  }

  @Override
  public void validateCharacteristicsLimit(List<Long> characteristicsIds) {
    petUtils.verifyCharacteristicsLimit(characteristicsIds);
  }

  @Override
  public void validateMultipartFilePetPictures(
      List<MultipartFile> petPictures) {
    final long TWO_MB_MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024;
    final List<String> ALLOWED_EXTENSIONS =
        Arrays.asList("png", "jpeg", "jpg", "webp", "jfif");

    validatePetPicturesNotEmpty(petPictures);
    validatePetPicturesLimit(petPictures);

    for (MultipartFile file : petPictures) {
      if (file.getSize() > TWO_MB_MAX_FILE_SIZE_BYTES) {
        throw new BadRequestException(
            "O tamanho do arquivo "
                + file.getName()
                + " excede o limite permitido");
      }

      if (!ALLOWED_EXTENSIONS.contains(fileUtils.getFileExtension(file))) {
        throw new BadRequestException(
            "Tipo de arquivo inválido. Tipos permitidos são: "
                + ALLOWED_EXTENSIONS);
      }
    }
  }

  @Override
  public void validateEncodedPetPictures(List<String> petPictures) {
    validatePetPicturesNotEmpty(petPictures);
    validatePetPicturesLimit(petPictures);
  }

  public <T> void validatePetPicturesNotEmpty(List<T> petPictures) {
    if (petPictures.isEmpty()) {
      throw new BadRequestException("A foto é obrigatoria");
    }
  }

  public <T> void validatePetPicturesLimit(List<T> petPictures) {
    int PICTURES_LIMIT = 5;

    if (petPictures.size() > PICTURES_LIMIT) {
      throw new BadRequestException("O pet deve ter entre 1 e 5 fotos");
    }
  }
}
