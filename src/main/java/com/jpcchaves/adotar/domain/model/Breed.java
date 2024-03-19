package com.jpcchaves.adotar.domain.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "breeds")
public class Breed {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60, nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "animal_type_id")
  private AnimalType animalType;

  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "breed")
  private List<Pet> petList = new ArrayList<>();

  public Breed() {}

  public Breed(Long id, String name, AnimalType animalType, List<Pet> petList) {
    this.id = id;
    this.name = name;
    this.animalType = animalType;
    this.petList = petList;
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

  public AnimalType getAnimalType() {
    return animalType;
  }

  public void setAnimalType(AnimalType animalType) {
    this.animalType = animalType;
  }

  public List<Pet> getPetList() {
    return petList;
  }

  public void setPetList(List<Pet> petList) {
    this.petList = petList;
  }
}
