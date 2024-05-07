package br.com.jpcchaves.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Pet {

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
  private boolean isActive;
  private Set<PetCharacteristic> characteristics = new HashSet<>();
  private AnimalType type;
  private Breed breed;
  private List<PetPicture> petPictures = new ArrayList<>();
  private User user;
  private Address address;
  private String serialNumber;
  private Date deletedAt;

  public Pet(Long id, String name, int yearsAge, int monthsAge, AnimalGender gender,
      AnimalSize size,
      HealthCondition healthCondition, String color, String description, int visualizations,
      boolean isAvailable, Date adoptionDate, boolean isActive,
      Set<PetCharacteristic> characteristics, AnimalType type, Breed breed,
      List<PetPicture> petPictures, User user, Address address, String serialNumber,
      Date deletedAt) {
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
    this.isActive = isActive;
    this.characteristics = characteristics;
    this.type = type;
    this.breed = breed;
    this.petPictures = petPictures;
    this.user = user;
    this.address = address;
    this.serialNumber = serialNumber;
    this.deletedAt = deletedAt;
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

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public Set<PetCharacteristic> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(Set<PetCharacteristic> characteristics) {
    this.characteristics = characteristics;
  }

  public AnimalType getType() {
    return type;
  }

  public void setType(AnimalType type) {
    this.type = type;
  }

  public Breed getBreed() {
    return breed;
  }

  public void setBreed(Breed breed) {
    this.breed = breed;
  }

  public List<PetPicture> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<PetPicture> petPictures) {
    this.petPictures = petPictures;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }
}
