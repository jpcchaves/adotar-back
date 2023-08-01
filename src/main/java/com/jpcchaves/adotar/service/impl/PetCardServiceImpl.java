package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.repository.PetRepository;
import com.jpcchaves.adotar.service.usecases.PetCardService;
import com.jpcchaves.adotar.utils.base64.Base64Utils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class PetCardServiceImpl implements PetCardService {
    private static final boolean ACTIVE = true;
    private final PetRepository petRepository;

    public PetCardServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public byte[] generatePetCard(Long petId) throws IOException {

        Pet pet = getPetById(petId);

        String PET_CARD_ID_PATH = "/templates/petcard.pdf";
        ClassPathResource templateResource = new ClassPathResource(PET_CARD_ID_PATH);
        PDDocument pdfDocument = PDDocument.load(templateResource.getInputStream());

        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();

        setPetCardFields(pet, pdfDocument, acroForm);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfDocument.save(outputStream);
        pdfDocument.close();

        return outputStream.toByteArray();
    }

    private void setPetCardFields(Pet pet,
                                  PDDocument pdfDocument,
                                  PDAcroForm acroForm) throws IOException {

        handlePetPictureInput(pet, pdfDocument, acroForm);

        acroForm.getField("name").setValue(pet.getName());
        acroForm.getField("age").setValue(generatePetAgeMessage(pet));
        acroForm.getField("breed").setValue(pet.getBreed().getName());
        acroForm.getField("gender").setValue(generatePetGenderMessage(pet));
        acroForm.getField("type").setValue(pet.getType().getType());
        acroForm.getField("color").setValue(pet.getColor());
        acroForm.getField("size").setValue(generatePetSize(pet));
        acroForm.getField("characteristics").setValue(generateCharacteristics(pet));
        acroForm.getField("address").setValue(generateAddress(pet));
        acroForm.getField("observations").setValue(pet.getDescription());
        acroForm.getField("owner_name").setValue(generateOwnerName(pet));
    }

    private String generateAddress(Pet pet) {
        return pet.getAddress().getCity() + " - " + pet.getAddress().getState();
    }

    private String generateOwnerName(Pet pet) {
        return pet.getUser().getFirstName() + " " + pet.getUser().getLastName();
    }

    private Pet getPetById(Long petId) {
        return petRepository
                .findByIdAndActive(petId, ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + petId));
    }

    private void handlePetPictureInput(Pet pet,
                                       PDDocument pdfDocument,
                                       PDAcroForm acroForm) throws IOException {
        PDField imageField = acroForm.getField("picture");
        PDPage page = pdfDocument.getPage(0);
        PDRectangle position = imageField.getWidgets().get(0).getRectangle();
        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page, PDPageContentStream.AppendMode.APPEND, true, false);

        String base64Image = extractBase64(pet);
        byte[] imageBytes = base64ToByteArray(base64Image);
        PDImageXObject image = PDImageXObject.createFromByteArray(pdfDocument, imageBytes, "image/jpeg");

        contentStream.drawImage(image, position.getLowerLeftX(), position.getLowerLeftY(), position.getWidth(), position.getHeight());
        contentStream.close();
    }

    private byte[] base64ToByteArray(String base64String) {
        if (Base64Utils.isValidBase64(base64String)) {
            return Base64.getMimeDecoder().decode(base64String);
        }
        throw new IllegalArgumentException("invalid base64");
    }

    private String extractBase64(Pet pet) {
        String pictureBase64 = pet.getPetPictures().get(0).getImgUrl();

        if (Base64Utils.hasBase64Prefix(pictureBase64)) {
            return Base64Utils.removeBase64Prefix(pictureBase64);
        }

        return pictureBase64;
    }

    private String generateCharacteristics(Pet pet) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (PetCharacteristic characteristic : pet.getCharacteristics()) {
            if (!isFirst) {
                sb.append(", ");
            } else {
                isFirst = false;
            }
            sb.append(characteristic.getCharacteristic());
        }

        return sb.toString();
    }

    private String generatePetAgeMessage(Pet pet) {
        return pet.getYearsAge() > 0 ? pet.getYearsAge() + " anos e " + pet.getMonthsAge() + " meses" : pet.getMonthsAge() + " meses";
    }

    private String generatePetGenderMessage(Pet pet) {
        return pet.getGender() == AnimalGender.MALE ? "Macho" : "Fêmea";
    }

    private String generatePetSize(Pet pet) {

        if (pet.getSize() == AnimalSize.TINY) {
            return "Muito Pequeno";
        }

        if (pet.getSize() == AnimalSize.SMALL) {
            return "Pequeno";
        }


        if (pet.getSize() == AnimalSize.MEDIUM) {
            return "Médio";
        }

        return "Grande";
    }
}
