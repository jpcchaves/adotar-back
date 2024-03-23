package com.jpcchaves.adotar.application.service.impl.v1;

import com.jpcchaves.adotar.application.service.usecases.PetCardService;
import com.jpcchaves.adotar.application.utils.base64.Base64Utils;
import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.exception.PdfNotAvailableException;
import com.jpcchaves.adotar.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.domain.model.Pet;
import com.jpcchaves.adotar.domain.model.PetCharacteristic;
import com.jpcchaves.adotar.infra.repository.PetRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

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

@Service
public class PetCardServiceImpl implements PetCardService {
    private final PetRepository petRepository;

    public PetCardServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public byte[] generatePetCard(Long petId) {

        Pet pet = getPetById(petId);

        String PET_CARD_ID_PATH = "/templates/petcard.pdf";
        try {
            ClassPathResource templateResource =
                    new ClassPathResource(PET_CARD_ID_PATH);
            PDDocument pdfDocument =
                    PDDocument.load(templateResource.getInputStream());

            PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();

            PDAcroForm acroForm = docCatalog.getAcroForm();

            setPetCardFields(pet, pdfDocument, acroForm);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            pdfDocument.save(outputStream);
            pdfDocument.close();

            return outputStream.toByteArray();

        } catch (IOException ex) {
            throw new PdfNotAvailableException(
                    "Ocorreu um erro inesperado ao gerar o cartão. Tente novamente mais tarde.");
        }
    }

    @Override
    public byte[] generateEmptyCard() {
        String PET_CARD_ID_PATH = "/templates/petcard.pdf";
        try {
            ClassPathResource templateResource =
                    new ClassPathResource(PET_CARD_ID_PATH);
            PDDocument pdfDocument =
                    PDDocument.load(templateResource.getInputStream());

            PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();

            PDAcroForm acroForm = docCatalog.getAcroForm();

            handlePetPictureInput(pdfDocument, acroForm);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            pdfDocument.save(outputStream);
            pdfDocument.close();

            return outputStream.toByteArray();

        } catch (IOException ex) {
            throw new PdfNotAvailableException(
                    "Ocorreu um erro inesperado ao gerar o cartão. Tente novamente mais tarde.");
        }
    }

    private void setPetCardFields(
            Pet pet,
            PDDocument pdfDocument,
            PDAcroForm acroForm) {

        handlePetPictureInput(pet, pdfDocument, acroForm);

        try {
            acroForm.getField("name").setValue(pet.getName());
            acroForm.getField("age").setValue(generatePetAgeMessage(pet));
            acroForm.getField("breed").setValue(pet.getBreed().getName());
            acroForm.getField("gender").setValue(generatePetGenderMessage(pet));
            acroForm.getField("type").setValue(pet.getType().getType());
            acroForm.getField("color").setValue(pet.getColor());
            acroForm.getField("size").setValue(generatePetSize(pet));
            acroForm
                    .getField("characteristics")
                    .setValue(generateCharacteristics(pet));
            acroForm.getField("address").setValue(generateAddress(pet));
            acroForm.getField("observations").setValue(pet.getDescription());
            acroForm.getField("owner_name").setValue(generateOwnerName(pet));
            acroForm.getField("serial_number").setValue(pet.getSerialNumber());
        } catch (IOException ex) {
            throw new PdfNotAvailableException(
                    "Ocorreu um erro inesperado. Tente novamente mais tarde");
        }
    }

    private String generateAddress(Pet pet) {
        return pet.getAddress().getCity() + " - " + pet.getAddress().getState();
    }

    private String generateOwnerName(Pet pet) {
        return pet.getUser().getFirstName() + " " + pet.getUser().getLastName();
    }

    private Pet getPetById(Long petId) {
        return petRepository
                .findByIdAndActiveTrue(petId)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Pet não encontrado com o ID informado: " + petId));
    }

    private void handlePetPictureInput(
            Pet pet,
            PDDocument pdfDocument,
            PDAcroForm acroForm) {
        try {
            PDField imageField = acroForm.getField("picture");
            PDPage page = pdfDocument.getPage(0);
            PDRectangle position = imageField.getWidgets().get(0).getRectangle();
            PDPageContentStream contentStream =
                    new PDPageContentStream(
                            pdfDocument,
                            page,
                            PDPageContentStream.AppendMode.APPEND,
                            true,
                            false);

            String base64Image = extractBase64(pet);
            byte[] imageBytes = base64ToByteArray(base64Image);
            PDImageXObject image =
                    PDImageXObject.createFromByteArray(
                            pdfDocument, imageBytes, "image/jpeg");

            contentStream.drawImage(
                    image,
                    position.getLowerLeftX(),
                    position.getLowerLeftY(),
                    position.getWidth(),
                    position.getHeight());
            contentStream.close();
        } catch (IOException ex) {
            throw new PdfNotAvailableException(
                    "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

    private void handlePetPictureInput(
            PDDocument pdfDocument,
            PDAcroForm acroForm) {
        try {
            PDField imageField = acroForm.getField("picture");
            PDPage page = pdfDocument.getPage(0);
            PDRectangle position = imageField.getWidgets().get(0).getRectangle();
            PDPageContentStream contentStream =
                    new PDPageContentStream(
                            pdfDocument,
                            page,
                            PDPageContentStream.AppendMode.APPEND,
                            true,
                            false);

            String base64Image = makeDefaultImage();
            byte[] imageBytes = base64ToByteArray(base64Image);
            PDImageXObject image =
                    PDImageXObject.createFromByteArray(
                            pdfDocument, imageBytes, "image/jpeg");

            contentStream.drawImage(
                    image,
                    position.getLowerLeftX(),
                    position.getLowerLeftY(),
                    position.getWidth(),
                    position.getHeight());
            contentStream.close();
        } catch (IOException ex) {
            throw new PdfNotAvailableException(
                    "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

    private byte[] base64ToByteArray(String base64String) {
        if (Base64Utils.isValidBase64(base64String)) {
            return Base64.getMimeDecoder().decode(base64String);
        }
        throw new IllegalArgumentException("invalid base64");
    }

    private String extractBase64(Pet pet) {
        if (!pet.getPetPictures().isEmpty()) {

            String pictureBase64 = pet.getPetPictures().get(0).getImgUrl();

            if (Base64Utils.hasBase64Prefix(pictureBase64)) {
                return Base64Utils.removeBase64Prefix(pictureBase64);
            }
            return pictureBase64;
        }

        return makeDefaultImage();
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
        return pet.getYearsAge() > 0
                ? pet.getYearsAge() + " anos e " + pet.getMonthsAge() + " meses"
                : pet.getMonthsAge() + " meses";
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

    private String makeDefaultImage() {
        return "/9j/4QAiRXhpZgAATU0AKgAAAAgAAQESAAMAAAABAAEAAAAAAAD/7RlQUGhvdG9zaG9wIDMuMAA4QklNBAQAAAAAAFkcAVoAAxslRxwCAAACAAAcAlAACkpvYW8gUGF1bG8cAgUANkRvY3VtZW50byBBNCBjZXJ0aWTDo28gZGUgYWRvw6fDo28gZGUgcGV0IGF6dWwgc2ltcGxlcwA4QklNBCUAAAAAABCphVfswud5U4WQM1TuzKzAOEJJTQQ6AAAAAAD5AAAAEAAAAAEAAAAAAAtwcmludE91dHB1dAAAAAUAAAAAUHN0U2Jvb2wBAAAAAEludGVlbnVtAAAAAEludGUAAAAASW1nIAAAAA9wcmludFNpeHRlZW5CaXRib29sAAAAAAtwcmludGVyTmFtZVRFWFQAAAABAAAAAAAPcHJpbnRQcm9vZlNldHVwT2JqYwAAABYAQwBvAG4AZgBpAGcAdQByAGEA5wDjAG8AIABkAGUAIABQAHIAbwB2AGEAAAAAAApwcm9vZlNldHVwAAAAAQAAAABCbHRuZW51bQAAAAxidWlsdGluUHJvb2YAAAAJcHJvb2ZDTVlLADhCSU0EOwAAAAACLQAAABAAAAABAAAAAAAScHJpbnRPdXRwdXRPcHRpb25zAAAAFwAAAABDcHRuYm9vbAAAAAAAQ2xicmJvb2wAAAAAAFJnc01ib29sAAAAAABDcm5DYm9vbAAAAAAAQ250Q2Jvb2wAAAAAAExibHNib29sAAAAAABOZ3R2Ym9vbAAAAAAARW1sRGJvb2wAAAAAAEludHJib29sAAAAAABCY2tnT2JqYwAAAAEAAAAAAABSR0JDAAAAAwAAAABSZCAgZG91YkBv4AAAAAAAAAAAAEdybiBkb3ViQG/gAAAAAAAAAAAAQmwgIGRvdWJAb+AAAAAAAAAAAABCcmRUVW50RiNSbHQAAAAAAAAAAAAAAABCbGQgVW50RiNSbHQAAAAAAAAAAAAAAABSc2x0VW50RiNQeGxAcsAAAAAAAAAAAAp2ZWN0b3JEYXRhYm9vbAEAAAAAUGdQc2VudW0AAAAAUGdQcwAAAABQZ1BDAAAAAExlZnRVbnRGI1JsdAAAAAAAAAAAAAAAAFRvcCBVbnRGI1JsdAAAAAAAAAAAAAAAAFNjbCBVbnRGI1ByY0BZAAAAAAAAAAAAEGNyb3BXaGVuUHJpbnRpbmdib29sAAAAAA5jcm9wUmVjdEJvdHRvbWxvbmcAAAAAAAAADGNyb3BSZWN0TGVmdGxvbmcAAAAAAAAADWNyb3BSZWN0UmlnaHRsb25nAAAAAAAAAAtjcm9wUmVjdFRvcGxvbmcAAAAAADhCSU0D7QAAAAAAEAEsAAAAAQACASwAAAABAAI4QklNBCYAAAAAAA4AAAAAAAAAAAAAP4AAADhCSU0EDQAAAAAABAAAAFo4QklNBBkAAAAAAAQAAAAeOEJJTQPzAAAAAAAJAAAAAAAAAAABADhCSU0nEAAAAAAACgABAAAAAAAAAAI4QklNA/UAAAAAAEgAL2ZmAAEAbGZmAAYAAAAAAAEAL2ZmAAEAoZmaAAYAAAAAAAEAMgAAAAEAWgAAAAYAAAAAAAEANQAAAAEALQAAAAYAAAAAAAE4QklNA/gAAAAAAHAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAOEJJTQQAAAAAAAACAAI4QklNBAIAAAAAAAYAAAAAAAA4QklNBDAAAAAAAAMBAQEAOEJJTQQtAAAAAAAGAAEAAAAKOEJJTQQIAAAAAAAQAAAAAQAAAkAAAAJAAAAAADhCSU0EHgAAAAAABAAAAAA4QklNBBoAAAAAA0MAAAAGAAAAAAAAAAAAAAKPAAAB6AAAAAcAcABlAHQAYwBhAHIAZAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAB6AAAAo8AAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwAAAACAAAABmJvdW5kc09iamMAAAABAAAAAAAAUmN0MQAAAAQAAAAAVG9wIGxvbmcAAAAAAAAAAExlZnRsb25nAAAAAAAAAABCdG9tbG9uZwAAAo8AAAAAUmdodGxvbmcAAAHoAAAABnNsaWNlc1ZsTHMAAAABT2JqYwAAAAEAAAAAAAVzbGljZQAAABIAAAAHc2xpY2VJRGxvbmcAAAAAAAAAB2dyb3VwSURsb25nAAAAAAAAAAZvcmlnaW5lbnVt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RSUa/wCbb8B+RSRO5UpJJJBSkkkklKSSSSUpJJJJSkkkklKSSSSUpJJJJT//0PU6/wCbb8B+RSUa/wCbb8B+RSRO5UpJJJBSkkkklKSSSSUpJJJJSkkkklKSSSSUpJJJJT//2ThCSU0EIQAAAAAAVwAAAAEBAAAADwBBAGQAbwBiAGUAIABQAGgAbwB0AG8AcwBoAG8AcAAAABQAQQBkAG8AYgBlACAAUABoAG8AdABvAHMAaABvAHAAIAAyADAAMgAxAAAAAQA4QklNBAYAAAAAAAcAAgAAAAEBAP/hElxodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDYuMC1jMDAyIDc5LjE2NDQ4OCwgMjAyMC8wNy8xMC0yMjowNjo1MyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnBkZj0iaHR0cDovL25zLmFkb2JlLmNvbS9wZGYvMS4zLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczphZGhvY3dmPSJodHRwOi8vbnMuYWRvYmUuY29tL0Fjcm9iYXRBZGhvY1dvcmtmbG93LzEuMC8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiB4bXA6Q3JlYXRlRGF0ZT0iMjAyMy0wOC0wMVQxMTo1Njo0M1oiIHhtcDpDcmVhdG9yVG9vbD0iQ2FudmEiIHhtcDpNb2RpZnlEYXRlPSIyMDIzLTA4LTAxVDE2OjA2OjMyLTAzOjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIzLTA4LTAxVDE2OjA2OjMyLTAzOjAwIiBwZGY6S2V5d29yZHM9IkRBRnFRUmpqak80LEJBRU1xdVQtNk1rIiBwZGY6UHJvZHVjZXI9IkNhbnZhIiB4bXBNTTpEb2N1bWVudElEPSJhZG9iZTpkb2NpZDpwaG90b3Nob3A6MmZhNWM5N2MtOTg0MC1iNDRiLTgyOTAtMzZiZTJkYjAyMDE3IiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjRlNWNiNDFmLWU5NTgtZmY0MC1hNjYxLWU3MWI4NDAyYzllYyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSJ1dWlkOmJhZTQ2NDZkLTgxZTAtNDY5ZC04ODM2LTllNjFhNzFiMTBkYSIgYWRob2N3ZjpzdGF0ZT0iMSIgYWRob2N3Zjp2ZXJzaW9uPSIxLjEiIHBob3Rvc2hvcDpDb2xvck1vZGU9IjMiIHBob3Rvc2hvcDpJQ0NQcm9maWxlPSJBZG9iZSBSR0IgKDE5OTgpIj4gPGRjOmNyZWF0b3I+IDxyZGY6U2VxPiA8cmRmOmxpPkpvYW8gUGF1bG88L3JkZjpsaT4gPC9yZGY6U2VxPiA8L2RjOmNyZWF0b3I+IDxkYzp0aXRsZT4gPHJkZjpBbHQ+IDxyZGY6bGkgeG1sOmxhbmc9IngtZGVmYXVsdCI+RG9jdW1lbnRvIEE0IGNlcnRpZMOjbyBkZSBhZG/Dp8OjbyBkZSBwZXQgYXp1bCBzaW1wbGVzPC9yZGY6bGk+IDwvcmRmOkFsdD4gPC9kYzp0aXRsZT4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iY29udmVydGVkIiBzdEV2dDpwYXJhbWV0ZXJzPSJmcm9tIGFwcGxpY2F0aW9uL3BkZiB0byBhcHBsaWNhdGlvbi92bmQuYWRvYmUucGhvdG9zaG9wIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDo3YTkxNmI3NC00ZjczLTc2NGQtYTRlNi0yMWY2ZTU2NjM3YjAiIHN0RXZ0OndoZW49IjIwMjMtMDgtMDFUMTY6MDY6MzItMDM6MDAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCAyMi4wIChXaW5kb3dzKSIgc3RFdnQ6Y2hhbmdlZD0iLyIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iY29udmVydGVkIiBzdEV2dDpwYXJhbWV0ZXJzPSJmcm9tIGFwcGxpY2F0aW9uL3BkZiB0byBpbWFnZS9qcGVnIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJkZXJpdmVkIiBzdEV2dDpwYXJhbWV0ZXJzPSJjb252ZXJ0ZWQgZnJvbSBhcHBsaWNhdGlvbi92bmQuYWRvYmUucGhvdG9zaG9wIHRvIGltYWdlL2pwZWciLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjRlNWNiNDFmLWU5NTgtZmY0MC1hNjYxLWU3MWI4NDAyYzllYyIgc3RFdnQ6d2hlbj0iMjAyMy0wOC0wMVQxNjowNjozMi0wMzowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIDIyLjAgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo3YTkxNmI3NC00ZjczLTc2NGQtYTRlNi0yMWY2ZTU2NjM3YjAiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6N2E5MTZiNzQtNGY3My03NjRkLWE0ZTYtMjFmNmU1NjYzN2IwIiBzdFJlZjpvcmlnaW5hbERvY3VtZW50SUQ9InV1aWQ6YmFlNDY0NmQtODFlMC00NjlkLTg4MzYtOWU2MWE3MWIxMGRhIi8+IDxwaG90b3Nob3A6RG9jdW1lbnRBbmNlc3RvcnM+IDxyZGY6QmFnPiA8cmRmOmxpPjJERjhCODJCMDBDOTQzQjRCOTk1QjY3NkUyRTI3M0YwPC9yZGY6bGk+IDwvcmRmOkJhZz4gPC9waG90b3Nob3A6RG9jdW1lbnRBbmNlc3RvcnM+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDw/eHBhY2tldCBlbmQ9InciPz7/4gJASUNDX1BST0ZJTEUAAQEAAAIwQURCRQIQAABtbnRyUkdCIFhZWiAHzwAGAAMAAAAAAABhY3NwQVBQTAAAAABub25lAAAAAAAAAAAAAAAAAAAAAAAA9tYAAQAAAADTLUFEQkUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAApjcHJ0AAAA/AAAADJkZXNjAAABMAAAAGt3dHB0AAABnAAAABRia3B0AAABsAAAABRyVFJDAAABxAAAAA5nVFJDAAAB1AAAAA5iVFJDAAAB5AAAAA5yWFlaAAAB9AAAABRnWFlaAAACCAAAABRiWFlaAAACHAAAABR0ZXh0AAAAAENvcHlyaWdodCAxOTk5IEFkb2JlIFN5c3RlbXMgSW5jb3Jwb3JhdGVkAAAAZGVzYwAAAAAAAAARQWRvYmUgUkdCICgxOTk4KQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWFlaIAAAAAAAAPNRAAEAAAABFsxYWVogAAAAAAAAAAAAAAAAAAAAAGN1cnYAAAAA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";
    }
}
