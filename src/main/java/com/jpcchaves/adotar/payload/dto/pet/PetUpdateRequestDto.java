package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PetUpdateRequestDto {
    private Long id;
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
    private int visualizations;
    private boolean isAvailable;
    private Date adoptionDate;
    private boolean active;

    @Size(min = 1, max = 5, message = "O pet deve ter entre 1 e 5 características")
    private List<Long> characteristicsIds = new ArrayList<>();

    @Size(min = 1, max = 5, message = "O pet deve ter entre 1 e 5 fotos")
    private List<String> petPictures = new ArrayList<>();

    @NotNull(message = "O tipo do animal é obrigatório")
    private Long typeId;

    @NotNull(message = "A raça do animal é obrigatória")
    private Long breedId;

    public PetUpdateRequestDto() {
    }

    public PetUpdateRequestDto(Long id,
                               String name,
                               int yearsAge,
                               int monthsAge,
                               char gender,
                               char size,
                               char healthCondition,
                               String color,
                               String description,
                               int visualizations,
                               boolean active,
                               boolean isAvailable,
                               Date adoptionDate,
                               List<Long> characteristicsIds,
                               List<String> petPictures,
                               Long typeId,
                               Long breedId) {
        this.id = id;
        this.name = name;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        this.gender = gender;
        this.size = size;
        this.healthCondition = healthCondition;
        this.color = color;
        this.description = description;
        this.visualizations = visualizations;
        this.active = active;
        this.adoptionDate = adoptionDate;
        this.isAvailable = isAvailable;
        this.characteristicsIds = characteristicsIds;
        this.petPictures = petPictures;
        this.typeId = typeId;
        this.breedId = breedId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getVisualizations() {
        return visualizations;
    }

    public void setVisualizations(int visualizations) {
        this.visualizations = visualizations;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(Date adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Long> getCharacteristicsIds() {
        return characteristicsIds;
    }

    public void setCharacteristicsIds(List<Long> characteristicsIds) {
        this.characteristicsIds = characteristicsIds;
    }

    public List<String> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<String> petPictures) {
        this.petPictures = petPictures;
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
}
