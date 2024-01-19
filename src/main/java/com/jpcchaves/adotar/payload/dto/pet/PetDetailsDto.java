package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;
import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
import com.jpcchaves.adotar.payload.dto.address.AddressResponseDto;

import java.util.*;

public class PetDetailsDto {
    private Long id;
    private String name;
    private int yearsAge;
    private int monthsAge;
    private AnimalGender gender;
    private AnimalSize size;
    private HealthCondition healthCondition;
    private String color;
    private String description;
    private int visualizations;
    private boolean isAvailable;
    private Date adoptionDate;
    private boolean isFavorite;
    private boolean active;
    private Set<Long> characteristics = new HashSet<>();
    private AnimalTypeMinDto type;
    private BreedDto breed;
    private List<PetPictureDto> petPictures = new ArrayList<>();
    private AddressResponseDto address;

    public PetDetailsDto() {
    }

    public PetDetailsDto(Long id,
                         String name,
                         int yearsAge,
                         int monthsAge,
                         AnimalGender gender,
                         AnimalSize size,
                         HealthCondition healthCondition,
                         String color,
                         String description,
                         int visualizations,
                         boolean isAvailable,
                         Date adoptionDate,
                         boolean isFavorite,
                         boolean active,
                         Set<Long> characteristics,
                         AnimalTypeMinDto type,
                         BreedDto breed,
                         List<PetPictureDto> petPictures,
                         AddressResponseDto address) {
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
        this.isAvailable = isAvailable;
        this.adoptionDate = adoptionDate;
        this.isFavorite = isFavorite;
        this.active = active;
        this.characteristics = characteristics;
        this.type = type;
        this.breed = breed;
        this.petPictures = petPictures;
        this.address = address;
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

    public AnimalGender getGender() {
        return gender;
    }

    public void setGender(AnimalGender gender) {
        this.gender = gender;
    }

    public AnimalSize getSize() {
        return size;
    }

    public void setSize(AnimalSize size) {
        this.size = size;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(HealthCondition healthCondition) {
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Long> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<PetCharacteristic> characteristics) {
        Set<Long> characteristicsIds = new HashSet<>();

        for (PetCharacteristic characteristic : characteristics
        ) {
            characteristicsIds.add(characteristic.getId());
        }

        this.characteristics = characteristicsIds;
    }

    public AnimalTypeMinDto getType() {
        return type;
    }

    public void setType(AnimalTypeMinDto type) {
        this.type = type;
    }

    public BreedDto getBreed() {
        return breed;
    }

    public void setBreed(BreedDto breed) {
        this.breed = breed;
    }

    public List<PetPictureDto> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<PetPictureDto> petPictures) {
        this.petPictures = petPictures;
    }

    public AddressResponseDto getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }
}
