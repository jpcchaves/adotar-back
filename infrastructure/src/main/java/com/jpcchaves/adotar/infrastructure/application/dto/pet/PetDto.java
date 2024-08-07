package com.jpcchaves.adotar.infrastructure.application.dto.pet;

import com.jpcchaves.adotar.infrastructure.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.infrastructure.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.infrastructure.domain.Enum.HealthCondition;
import com.jpcchaves.adotar.infrastructure.domain.model.AnimalType;
import com.jpcchaves.adotar.infrastructure.domain.model.Breed;
import java.util.*;

public class PetDto {
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
  private boolean active;
  private Set<PetCharacteristicsDto> characteristics = new HashSet<>();
  private String type;
  private String breed;
  private List<PetPictureMinDto> petPictures = new ArrayList<>();

  public PetDto() {}

  public PetDto(
      Long id,
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
      List<PetPictureMinDto> petPictures) {
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

  public List<PetPictureMinDto> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<PetPictureMinDto> petPictures) {
    this.petPictures = petPictures;
  }
}
