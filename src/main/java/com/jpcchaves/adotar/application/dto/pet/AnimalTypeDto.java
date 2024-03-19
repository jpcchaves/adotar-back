package com.jpcchaves.adotar.application.dto.pet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalTypeDto {
  private Long id;
  private String type;
  private Set<BreedDto> breeds = new HashSet<>();
  private List<PetDto> petList = new ArrayList<>();

  public AnimalTypeDto() {}

  public AnimalTypeDto(
      Long id, String type, Set<BreedDto> breeds, List<PetDto> petList) {
    this.id = id;
    this.type = type;
    this.breeds = breeds;
    this.petList = petList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Set<BreedDto> getBreeds() {
    return breeds;
  }

  public void setBreeds(Set<BreedDto> breeds) {
    this.breeds = breeds;
  }

  public List<PetDto> getPetList() {
    return petList;
  }

  public void setPetList(List<PetDto> petList) {
    this.petList = petList;
  }
}
