package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.*;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.*;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.repository.*;
import com.jpcchaves.adotar.service.usecases.PetService;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.utils.pet.PetUtils;
import com.jpcchaves.adotar.utils.user.UserUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PetServiceImpl implements PetService {
    private final boolean ACTIVE = true;

    private final PetRepository petRepository;
    private final PetCharacteristicRepository petCharacteristicRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final PetPictureRepository petPictureRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final SecurityContextService securityContextService;
    private final UserSavedPetsRepository userSavedPetsRepository;
    private final GlobalUtils globalUtils;
    private final UserUtils userUtils;
    private final MapperUtils mapper;

    public PetServiceImpl(PetRepository petRepository,
                          PetCharacteristicRepository petCharacteristicRepository,
                          AnimalTypeRepository animalTypeRepository,
                          BreedRepository breedRepository,
                          PetPictureRepository petPictureRepository,
                          AddressRepository addressRepository,
                          CityRepository cityRepository,
                          SecurityContextService securityContextService,
                          UserSavedPetsRepository userSavedPetsRepository,
                          GlobalUtils globalUtils,
                          UserUtils userUtils,
                          MapperUtils mapper) {
        this.petRepository = petRepository;
        this.petCharacteristicRepository = petCharacteristicRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
        this.petPictureRepository = petPictureRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.securityContextService = securityContextService;
        this.userUtils = userUtils;
        this.userSavedPetsRepository = userSavedPetsRepository;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDto> listAll(Pageable pageable) {
        Page<Pet> petsPage = petRepository.findAllByActive(pageable, ACTIVE);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    @Override
    public PetDto getById(Long id) {
        Pet pet = petRepository.findByIdAndActive(id, ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + id));

        PetUtils.increasePetVisualization(pet);
        petRepository.save(pet);

        return mapper.parseObject(pet, PetDto.class);
    }

    @Override
    public ApiMessageResponseDto create(PetCreateRequestDto petCreateRequestDto) {
        Breed breed = getBreedByIdAndAnimalType(petCreateRequestDto.getBreedId(), petCreateRequestDto.getTypeId());

        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petCreateRequestDto.getCharacteristicsIds());

        AnimalType animalType = animalTypeRepository
                .findById(petCreateRequestDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de animal não encotrado!"));

        City city = cityRepository
                .findById(petCreateRequestDto.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));

        Address address = addressRepository.save(buildAddress(petCreateRequestDto, city));

        List<PetPicture> petPictures = mapper.parseListObjects(petCreateRequestDto.getPetPictures(), PetPicture.class);

        Pet pet = PetUtils.buildPetCreate(petCreateRequestDto, animalType, breed, characteristicsList, address);

        Pet savedPet = petRepository.save(pet);

        pet.setUser(securityContextService.getCurrentLoggedUser());

        for (PetPicture picture : petPictures) {
            picture.setPet(savedPet);
        }

        if (pictureExists(petCreateRequestDto.getPetPictures())) {
            petPictureRepository.saveAll(petPictures);
        }

        return new ApiMessageResponseDto("Pet criado com sucesso: " + petCreateRequestDto.getName());
    }

    @Override
    public ApiMessageResponseDto update(Long id,
                                        PetUpdateRequestDto petDto) {
        Pet pet = getPetById(id);

        Breed breed = getBreedByIdAndAnimalType(petDto.getBreedId(), petDto.getTypeId());

        List<PetCharacteristic> characteristicsList = petCharacteristicRepository
                .findAllById(petDto.getCharacteristicsIds());

        AnimalType animalType = animalTypeRepository
                .findById(petDto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("O tipo de animal informado não existe"));

        PetUtils.updatePetAttributes(pet, petDto);

        pet.setType(animalType);
        pet.setBreed(breed);
        pet.setCharacteristics(CollectionsUtils.convertListToSet(characteristicsList));

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet atualizado com sucesso");
    }

    @Override
    public ApiMessageResponseDto delete(Long id) {
        Pet pet = getPetById(id);

        inactivatePet(pet);

        petRepository.save(pet);

        return new ApiMessageResponseDto("Pet deletado com sucesso");
    }

    @Override
    public ApiResponsePaginatedDto<PetDto> getAllByUserId(Pageable pageable) {
        Page<Pet> petPage = petRepository.getAllByUser_Id(pageable, securityContextService.getCurrentLoggedUser().getId());
        List<PetDto> petDtoList = mapper.parseListObjects(petPage.getContent(), PetDto.class);
        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }

    @Override
    public Set<PetDto> getUserSavedPets() {
        User user = securityContextService.getCurrentLoggedUser();
        List<UserSavedPets> userSavedPets = userSavedPetsRepository.findAllByUserId(user.getId());

        if (isSavedPetsEmpty(userSavedPets)) {
            return buildEmptyList();
        }

        Set<Pet> savedPets = extractPets(userSavedPets);

        return mapper.parseSetObjects(savedPets, PetDto.class);
    }

    @Override
    public ApiMessageResponseDto addUserSavedPet(Long petId) {
        Pet pet = getPetById(petId);

        User user = securityContextService.getCurrentLoggedUser();

        if (existsByPetAndUser(petId, user.getId())) {
            throw new BadRequestException("O pet já foi salvo para o usuário informado");
        }

        userSavedPetsRepository.save(new UserSavedPets(user, pet));

        return new ApiMessageResponseDto("Pet salvo com sucesso!");
    }

    @Override
    public ApiMessageResponseDto removeUserSavedPet(Long petId) {
        User user = securityContextService.getCurrentLoggedUser();

        UserSavedPets userSavedPets = findByUserAndPet(user, petId);

        userSavedPetsRepository.delete(userSavedPets);

        return new ApiMessageResponseDto("Pet salvo removido com sucesso!");
    }

    @Override
    public ApiResponsePaginatedDto<PetMinDto> filterByBreedOrAnimalType(Pageable pageable,
                                                                        Long breedId,
                                                                        Long animalTypeId) {
        if (breedAndAnimalTypeIsPresent(breedId, animalTypeId)) {
            return doFilterByBreedAndAnimalType(pageable, breedId, animalTypeId);
        }

        if (animalTypeIsPresent(animalTypeId)) {
            return doFilterByAnimalType(pageable, animalTypeId);
        }

        if (breedIsPresent(breedId)) {
            return doFilterByBreed(pageable, breedId);
        }

        return listAll(pageable);
    }

    @Override
    public UserDetailsDto getPetOwnerDetails(Long petId) {
        Pet pet = getPetById(petId);
        User petOwner = pet.getUser();

        return userUtils.buildUserDetails(petOwner);
    }

    @Override
    public byte[] generatePetCard(Long petId) throws IOException {
        final String PET_CARD_ID_PATH = "/templates/petcard.pdf";

        Pet pet = getPetById(petId);

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
        PDField imageField = acroForm.getField("picture");
        PDPage page = pdfDocument.getPage(0);
        PDRectangle position = imageField.getWidgets().get(0).getRectangle();
        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page, PDPageContentStream.AppendMode.APPEND, true, false);

        String base64Image = pet.getPetPictures().get(0).getImgUrl();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        PDImageXObject image = PDImageXObject.createFromByteArray(pdfDocument, imageBytes, "image/jpeg");

        contentStream.drawImage(image, position.getLowerLeftX(), position.getLowerLeftY(), position.getWidth(), position.getHeight());

        acroForm.getField("name").setValue(pet.getName());
        acroForm.getField("age").setValue(generatePetAgeMessage(pet));
        acroForm.getField("breed").setValue(pet.getBreed().getName());
        acroForm.getField("gender").setValue(generatePetGenderMessage(pet));
        acroForm.getField("type").setValue(pet.getType().getType());
        acroForm.getField("color").setValue(pet.getColor());
        acroForm.getField("size").setValue(pet.getSize().name());
        acroForm.getField("characteristics").setValue(generateCharacteristics(pet));
        acroForm.getField("address").setValue(pet.getAddress().getCity() + " - " + pet.getAddress().getState());
        acroForm.getField("observations").setValue(pet.getDescription());
        acroForm.getField("owner_name").setValue(pet.getUser().getFirstName() + " " + pet.getUser().getLastName());

        contentStream.close();
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
        return pet.getGender().name().equals("MALE") ? "Macho" : "Fêmea";
    }

    private UserSavedPets findByUserAndPet(User user,
                                           Long petId) {
        return userSavedPetsRepository
                .findByPet_IdAndUser_Id(petId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("O usuário não possui o pet salvo"));
    }

    private void inactivatePet(Pet pet) {
        pet.setActive(false);
        pet.setDeletedAt(new Date());
    }

    private boolean isSavedPetsEmpty(List<UserSavedPets> userSavedPets) {
        return userSavedPets.isEmpty();
    }

    private Address buildAddress(PetCreateRequestDto petCreateRequestDto,
                                 City city) {
        return new Address(
                petCreateRequestDto.getZipcode(),
                petCreateRequestDto.getStreet(),
                petCreateRequestDto.getNumber(),
                petCreateRequestDto.getComplement(),
                petCreateRequestDto.getNeighborhood(),
                city.getName(),
                city.getState().getName()
        );
    }

    private boolean pictureExists(List<PetPictureDto> petPictures) {
        return petPictures.size() > 0;
    }

    private Pet getPetById(Long petId) {
        return petRepository
                .findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID informado: " + petId));
    }

    private boolean existsByPetAndUser(Long petId,
                                       Long userId) {
        return userSavedPetsRepository.existsByPet_IdAndUser_Id(petId, userId);
    }

    private <T> Set<T> buildEmptyList() {
        return new HashSet<>();
    }

    private Breed getBreedByIdAndAnimalType(Long breedId,
                                            Long typeId) {
        return breedRepository
                .findByIdAndAnimalType_Id(breedId, typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada!"));
    }

    private boolean breedAndAnimalTypeIsPresent(Long breedId,
                                                Long animalTypeId) {
        return breedId != null && animalTypeId != null;
    }

    private boolean animalTypeIsPresent(Long animalTypeId) {
        return animalTypeId != null;
    }

    private boolean breedIsPresent(Long breedId) {
        return breedId != null;
    }

    private ApiResponsePaginatedDto<PetMinDto> doFilterByBreedAndAnimalType(Pageable pageable,
                                                                            Long breedId,
                                                                            Long animalTypeId) {
        Page<Pet> petsPage = petRepository
                .getAllByBreed_IdAndType_Id(pageable, breedId, animalTypeId);

        List<PetMinDto> petDtoList = mapper
                .parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    private ApiResponsePaginatedDto<PetMinDto> doFilterByAnimalType(Pageable pageable,
                                                                    Long animalTypeId) {
        Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, animalTypeId);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    private ApiResponsePaginatedDto<PetMinDto> doFilterByBreed(Pageable pageable,
                                                               Long breedId) {
        Page<Pet> petsPage = petRepository.getAllByType_Id(pageable, breedId);
        List<PetMinDto> petDtoList = mapper.parseListObjects(petsPage.getContent(), PetMinDto.class);

        return globalUtils.buildApiResponsePaginated(petsPage, petDtoList);
    }

    private Set<Pet> extractPets(List<UserSavedPets> userSavedPets) {
        List<Pet> pets = new ArrayList<>();
        for (UserSavedPets userSavedPet : userSavedPets) {
            pets.add(userSavedPet.getPet());
        }

        return new HashSet<>(pets);
    }
}
