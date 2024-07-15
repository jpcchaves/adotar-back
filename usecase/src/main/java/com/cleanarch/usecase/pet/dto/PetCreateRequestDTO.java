package com.cleanarch.usecase.pet.dto;

import br.com.jpcchaves.core.domain.model.*;
import com.cleanarch.usecase.common.dto.*;
import java.util.*;

public class PetCreateRequestDTO {
  private String name;
  private int yearsAge;
  private int monthsAge;
  private char gender;
  private char size;
  private char healthCondition;
  private String color;
  private String description;
  private List<String> characteristics = new ArrayList<>();
  private Address address;
  private char animalType;
  private String breed;
  private List<PictureMinDTO> petPictures = new ArrayList<>();

  public PetCreateRequestDTO() {}

  public PetCreateRequestDTO(
      String name,
      int yearsAge,
      int monthsAge,
      char gender,
      char size,
      char healthCondition,
      String color,
      String description,
      List<String> characteristics,
      Address address,
      char animalType,
      String breed,
      List<PictureMinDTO> petPictures) {
    this.name = name;
    this.yearsAge = yearsAge;
    this.monthsAge = monthsAge;
    this.gender = gender;
    this.size = size;
    this.healthCondition = healthCondition;
    this.color = color;
    this.description = description;
    this.characteristics = characteristics;
    this.address = address;
    this.animalType = animalType;
    this.breed = breed;
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

  public List<String> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(List<String> characteristics) {
    this.characteristics = characteristics;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
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

  public List<PictureMinDTO> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<PictureMinDTO> petPictures) {
    this.petPictures = petPictures;
  }
}
