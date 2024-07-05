package com.cleanarch.usecase.pet.dto;

import com.cleanarch.usecase.common.dto.*;
import java.util.*;

public class PetMinDTO {
  private Long id;
  private String name;
  private String description;
  private char gender;
  private int visualizations;
  private String type;
  private String breed;
  private boolean isFavorite;
  private List<PictureDTO> petPictures = new ArrayList<>();
  private AddressMinDTO address;
  private Date createdAt;

  public PetMinDTO() {}

  public PetMinDTO(
      Long id,
      String name,
      String description,
      char gender,
      int visualizations,
      String type,
      String breed,
      boolean isFavorite,
      List<PictureDTO> petPictures,
      AddressMinDTO address,
      Date createdAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.gender = gender;
    this.visualizations = visualizations;
    this.type = type;
    this.breed = breed;
    this.isFavorite = isFavorite;
    this.petPictures = petPictures;
    this.address = address;
    this.createdAt = createdAt;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public void setFavorite(boolean favorite) {
    isFavorite = favorite;
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
