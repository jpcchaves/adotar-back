package com.jpcchaves.adotar.application.dto.pet.v2;

import com.jpcchaves.adotar.application.dto.address.AddressMinDto;
import com.jpcchaves.adotar.application.dto.pet.AnimalTypeDto;
import com.jpcchaves.adotar.application.dto.pet.BreedDto;
import com.jpcchaves.adotar.application.dto.pet.PetCharacteristicsDto;
import com.jpcchaves.adotar.application.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;
import com.jpcchaves.adotar.domain.model.AnimalType;
import com.jpcchaves.adotar.domain.model.Breed;
import java.util.*;

public class PetDtoV2 {
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
  private Set<PetCharacteristicsDto> characteristics = new HashSet<>();
  private String type;
  private String breed;
  private List<PetPictureDto> petPictures = new ArrayList<>();
  private AddressMinDto address;
  private String ownerName;

  public PetDtoV2() {}

  public PetDtoV2(
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
      boolean isFavorite,
      Set<PetCharacteristicsDto> characteristics,
      AnimalTypeDto type,
      BreedDto breed,
      List<PetPictureDto> petPictures,
      AddressMinDto address,
      String ownerName) {
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
    this.isFavorite = isFavorite;
    this.characteristics = characteristics;
    this.type = type.getType();
    this.breed = breed.getName();
    this.petPictures = petPictures;
    this.address = address;
    this.ownerName = ownerName;
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

  public List<PetPictureDto> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<PetPictureDto> petPictures) {
    this.petPictures = petPictures;
  }

  public AddressMinDto getAddress() {
    return address;
  }

  public void setAddress(AddressMinDto address) {
    this.address = address;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }
}
