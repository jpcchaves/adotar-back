package com.jpcchaves.adotar.application.dto.pet;

import com.jpcchaves.adotar.application.dto.address.AddressMinDto;
import com.jpcchaves.adotar.domain.model.Breed;
import java.util.ArrayList;
import java.util.List;

public class PetMinDto {
  private Long id;
  private String name;
  private char gender;
  private int visualizations;
  private String type;
  private String breed;
  private List<PetPictureMinDto> petPictures = new ArrayList<>();
  private AddressMinDto address;

  public PetMinDto() {}

  public PetMinDto(
      Long id,
      String name,
      char gender,
      int visualizations,
      String type,
      BreedDto breed,
      List<PetPictureMinDto> petPictures,
      AddressMinDto address) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.visualizations = visualizations;
    this.type = type;
    this.breed = breed.getName();
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

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public int getVisualizations() {
    return visualizations;
  }

  public void setVisualizations(int visualizations) {
    this.visualizations = visualizations;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(Breed breed) {
    this.breed = breed.getName();
  }

  public List<PetPictureMinDto> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<PetPictureMinDto> petPictures) {
    this.petPictures = petPictures;
  }

  public AddressMinDto getAddress() {
    return address;
  }

  public void setAddress(AddressMinDto address) {
    this.address = address;
  }
}
