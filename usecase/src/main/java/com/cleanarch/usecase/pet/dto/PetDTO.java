package com.cleanarch.usecase.pet.dto;

import br.com.jpcchaves.core.domain.enums.*;
import com.cleanarch.usecase.common.dto.*;
import java.util.*;

public class PetDTO {
  private Long id;
  private String name;
  private int yearsAge;
  private int monthsAge;
  private Gender gender;
  private AnimalSize size;
  private HealthCondition healthCondition;
  private String color;
  private String description;
  private int visualizations;
  private boolean isAvailable;
  private Date adoptionDate;
  private boolean isFavorite;
  private boolean active;
  private List<String> characteristics = new ArrayList<>();
  private String animalType;
  private String breed;
  private List<PictureDTO> petPictures = new ArrayList<>();
  private AddressMinDTO address;
  private String ownerName;

  public PetDTO() {}

  public PetDTO(
      Long id,
      String name,
      int yearsAge,
      int monthsAge,
      Gender gender,
      AnimalSize size,
      HealthCondition healthCondition,
      String color,
      String description,
      int visualizations,
      boolean isAvailable,
      Date adoptionDate,
      boolean isFavorite,
      boolean active,
      List<String> characteristics,
      String animalType,
      String breed,
      List<PictureDTO> petPictures,
      AddressMinDTO address,
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
    this.isAvailable = isAvailable;
    this.adoptionDate = adoptionDate;
    this.isFavorite = isFavorite;
    this.active = active;
    this.characteristics = characteristics;
    this.animalType = animalType;
    this.breed = breed;
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

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
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

  public List<String> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(List<String> characteristics) {
    this.characteristics = characteristics;
  }

  public String getAnimalType() {
    return animalType;
  }

  public void setAnimalType(String animalType) {
    this.animalType = animalType;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public List<PictureDTO> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<PictureDTO> petPictures) {
    this.petPictures = petPictures;
  }

  public AddressMinDTO getAddress() {
    return address;
  }

  public void setAddress(AddressMinDTO address) {
    this.address = address;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }
}
