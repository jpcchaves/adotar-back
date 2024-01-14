package com.jpcchaves.adotar.service.pet;

import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.service.pet.contracts.PetUtils;
import com.jpcchaves.adotar.service.pet.contracts.PetValidationService;
import com.jpcchaves.adotar.utils.files.contracts.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

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
    public void validatePetPictures(List<MultipartFile> petPictures) {
        final long TWO_MB_MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024;
        final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpeg", "jpg", "webp", "jfif");

        if (petPictures.isEmpty()) {
            throw new BadRequestException("O pet precisa ter pelo menos uma foto");
        }

        for (MultipartFile file : petPictures) {
            if (file.getSize() > TWO_MB_MAX_FILE_SIZE_BYTES) {
                throw new BadRequestException("O tamanho do arquivo " + file.getName() + " excede o limite permitido");
            }

            String fileExtension = fileUtils.getFileExtension(file);

            if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
                throw new BadRequestException("Tipo de arquivo inválido. Tipos permitidos são: " + ALLOWED_EXTENSIONS);
            }
        }

    }
}
