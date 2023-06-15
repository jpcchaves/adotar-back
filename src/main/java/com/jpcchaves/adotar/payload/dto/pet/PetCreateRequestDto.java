package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;

import java.util.ArrayList;
import java.util.List;

public class PetCreateRequestDto {
    private String name;
    private int yearsAge;
    private int monthsAge;
    private char gender;
    private char size;
    private char healthCondition;
    private String color;
    private String description;
    private boolean active;
    private boolean isAvailable;
    private List<Long> characteristicsIds = new ArrayList<>();
    private Long typeId;
    private Long breedId;

    public PetCreateRequestDto() {
    }

    public PetCreateRequestDto(String name,
                               int yearsAge,
                               int monthsAge,
                               AnimalGender gender,
                               AnimalSize size,
                               HealthCondition healthCondition,
                               String color,
                               String description,
                               boolean active,
                               boolean isAvailable,
                               List<Long> characteristicsIds,
                               Long typeId,
                               Long breedId) {
        this.name = name;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        setGender(gender);
        setSize(size);
        setHealthCondition(healthCondition);
        this.color = color;
        this.description = description;
        this.active = active;
        this.isAvailable = isAvailable;
        this.characteristicsIds = characteristicsIds;
        this.typeId = typeId;
        this.breedId = breedId;
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

    public AnimalGender getGender() {
        return AnimalGender.valueOf(gender);
    }

    public void setGender(AnimalGender gender) {
        if (gender != null) {
            this.gender = gender.getGender();
        }
    }

    public AnimalSize getSize() {
        return AnimalSize.valueOf(size);
    }

    public void setSize(AnimalSize size) {
        if (size != null) {
            this.size = size.getSize();
        }
    }

    public HealthCondition getHealthCondition() {
        return HealthCondition.valueOf(healthCondition);
    }

    public void setHealthCondition(HealthCondition healthCondition) {
        if (healthCondition != null) {
            this.healthCondition = healthCondition.getHealthCondition();
        }
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
}
