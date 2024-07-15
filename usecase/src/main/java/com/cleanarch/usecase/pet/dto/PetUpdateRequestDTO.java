package com.cleanarch.usecase.pet.dto;

import br.com.jpcchaves.core.domain.model.*;
import com.cleanarch.usecase.common.dto.*;

import java.util.*;

public class PetUpdateRequestDTO {
  private String name;
  private int yearsAge;
  private int monthsAge;
  private char gender;
  private char size;
  private char healthCondition;
  private String color;
  private String description;
  private boolean isAvailable;
  private List<String> characteristics = new ArrayList<>();
  private BaseAddressRequestDTO address;
  private List<Picture> petPictures = new ArrayList<>();
  private char animalType;
  private String breed;

  public PetUpdateRequestDTO() {
  }

  public PetUpdateRequestDTO(
      String name,
      int yearsAge,
      int monthsAge,
      char gender,
      char size,
      char healthCondition,
      String color,
      String description,
      boolean isAvailable,
      List<String> characteristics,
      BaseAddressRequestDTO address,
      List<Picture> petPictures,
      char animalType,
      String breed
  ) {
    this.name = name;
    this.yearsAge = yearsAge;
    this.monthsAge = monthsAge;
    this.gender = gender;
    this.size = size;
    this.healthCondition = healthCondition;
    this.color = color;
    this.description = description;
    this.isAvailable = isAvailable;
    this.characteristics = characteristics;
    this.address = address;
    this.petPictures = petPictures;
    this.animalType = animalType;
    this.breed = breed;
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

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public List<String> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(List<String> characteristics) {
    this.characteristics = characteristics;
  }

  public BaseAddressRequestDTO getAddress() {
    return address;
  }

  public void setAddress(BaseAddressRequestDTO address) {
    this.address = address;
  }

  public List<Picture> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<Picture> petPictures) {
    this.petPictures = petPictures;
  }

  public char getAnimalType() {
    return animalType;
  }

  public void setAnimalType(char animalType) {
    this.animalType = animalType;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }
}
