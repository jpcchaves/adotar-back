package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;
import com.jpcchaves.adotar.domain.entities.AnimalType;
import com.jpcchaves.adotar.domain.entities.Breed;

import java.util.*;

public class PetDto {
    private Long id;
    private String name;
    private int yearsAge;
    private int monthsAge;
    private char gender;
    private char size;
    private char healthCondition;
    private String color;
    private String description;
    private int visualizations;
    private boolean isAvailable;
    private Date adoptionDate;
    private boolean active;
    private Set<PetCharacteristicsDto> characteristics = new HashSet<>();
    private String type;
    private String breed;
    private List<PetPictureDto> petPictures = new ArrayList<>();

    public PetDto() {
    }

    public PetDto(Long id,
                  String name,
                  int yearsAge,
                  int monthsAge,
                  AnimalGender gender,
                  AnimalSize size,
                  HealthCondition healthCondition,
                  String color,
                  String description,
                  int visualizations,
                  boolean active,
                  boolean isAvailable,
                  Date adoptionDate,
                  Set<PetCharacteristicsDto> characteristics,
                  AnimalTypeDto type,
                  BreedDto breed,
                  List<PetPictureDto> petPictures) {
        this.id = id;
        this.name = name;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        setGender(gender);
        setSize(size);
        setHealthCondition(healthCondition);
        this.color = color;
        this.description = description;
        this.visualizations = visualizations;
        this.active = active;
        this.adoptionDate = adoptionDate;
        this.isAvailable = isAvailable;
        this.characteristics = characteristics;
        this.type = type.getType();
        this.breed = breed.getName();
        this.petPictures = petPictures;
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

    public Set<PetCharacteristicsDto> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<PetCharacteristicsDto> characteristics) {
        this.characteristics = characteristics;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(AnimalType type) {
        this.type = type.getType();
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed.getName();
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

    public List<PetPictureDto> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<PetPictureDto> petPictures) {
        this.petPictures = petPictures;
    }
}
