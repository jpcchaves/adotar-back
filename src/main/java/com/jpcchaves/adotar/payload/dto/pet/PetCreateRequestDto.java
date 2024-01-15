package com.jpcchaves.adotar.payload.dto.pet;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class PetCreateRequestDto {

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @PositiveOrZero(message = "A idade em anos deve ser um valor maior ou igual a 0")
    @Min(value = 0, message = "A idade em anos deve ser 0 ou maior")
    @Max(value = 25, message = "Verifique a idade em anos informada e tente novamente")
    @NotNull(message = "A idade em anos é obrigatória")
    private int yearsAge;

    @Min(value = 1, message = "A idade em meses deve ser um valor entre 1 e 11 (meses do ano)")
    @Max(value = 11, message = "A idade em meses deve ser um valor entre 1 e 11 (meses do ano)")
    @Positive(message = "A idade em meses deve ser um valor maior ou igual a 1")
    @NotNull(message = "A idade em meses é obrigatória")
    private int monthsAge;

    private char gender;
    private char size;
    private char healthCondition;

    @NotBlank(message = "A cor é obrigatória")
    private String color;
    private String description;
    private boolean active;
    private boolean isAvailable;

    @Size(min = 1, max = 5, message = "O pet deve ter entre 1 e 5 características")
    private List<Long> characteristicsIds = new ArrayList<>();

    @NotBlank(message = "O CEP é obrigatório")
    @Length(min = 8, max = 8, message = "CEP inválido")
    private String zipcode;

    @NotBlank(message = "A rua é obrigatória")
    private String street;

    @NotBlank(message = "O número é obrigatório")
    private String number;

    @NotBlank(message = "O complemento é obrigatório")
    private String complement;

    @NotBlank(message = "O bairro é obrigatório")
    private String neighborhood;

    @NotNull(message = "A cidade é obrigatória!")
    @Positive(message = "Dados inválidos, verifique os dados de cidade informados e tente novamente")
    private Long cityIbge;
    @NotNull(message = "O tipo do animal é obrigatório")
    private Long typeId;

    @NotNull(message = "A raça do animal é obrigatória")
    private Long breedId;

    @Size(min = 1, max = 5, message = "O pet deve ter entre 1 e 5 fotos")
    private List<MultipartFile> petPictures;

    public PetCreateRequestDto() {
    }

    public PetCreateRequestDto(String name,
                               int yearsAge,
                               int monthsAge,
                               char gender,
                               char size,
                               char healthCondition,
                               String color,
                               String description,
                               boolean active,
                               boolean isAvailable,
                               List<Long> characteristicsIds,
                               String zipcode,
                               String street,
                               String number,
                               String complement,
                               String neighborhood,
                               Long typeId,
                               Long breedId,
                               List<MultipartFile> petPictures) {
        this.name = name;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        this.zipcode = zipcode;
        this.size = size;
        this.healthCondition = healthCondition;
        this.gender = gender;
        this.color = color;
        this.description = description;
        this.active = active;
        this.isAvailable = isAvailable;
        this.characteristicsIds = characteristicsIds;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.typeId = typeId;
        this.breedId = breedId;
        this.petPictures = petPictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsAge() {
        return yearsAge;
    }

    public void setYearsAge(int yearsAge) {
        this.yearsAge = yearsAge;
    }

    public int getMonthsAge() {
        return monthsAge;
    }

    public void setMonthsAge(int monthsAge) {
        this.monthsAge = monthsAge;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public char getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(char healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<Long> getCharacteristicsIds() {
        return characteristicsIds;
    }

    public void setCharacteristicsIds(List<Long> characteristicsIds) {
        this.characteristicsIds = characteristicsIds;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }


    public List<MultipartFile> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<MultipartFile> petPictures) {
        this.petPictures = petPictures;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Long getCityIbge() {
        return cityIbge;
    }

    public void setCityIbge(Long cityIbge) {
        this.cityIbge = cityIbge;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
