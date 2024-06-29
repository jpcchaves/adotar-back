package br.com.jpcchaves.core.domain.model;

import br.com.jpcchaves.core.domain.enums.AnimalSize;
import br.com.jpcchaves.core.domain.enums.AnimalType;
import br.com.jpcchaves.core.domain.enums.Gender;
import br.com.jpcchaves.core.domain.enums.HealthCondition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Pet {

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

  private boolean active;

  private Set<String> characteristics = new HashSet<>();

  private AnimalType type;

  private String breed;

  private List<Picture> petPictures = new ArrayList<>();

  private User user;

  private Address address;

  private String serialNumber;

  private Date lastSeen;
  private Date createdAt;
  private Date updatedAt;
  private Date deletedAt;

  public Pet() {
  }

  public Pet(
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
      boolean active,
      Set<String> characteristics,
      AnimalType type,
      String breed,
      List<Picture> petPictures,
      User user,
      Address address,
      String serialNumber,
      Date lastSeen,
      Date createdAt,
      Date updatedAt,
      Date deletedAt
  ) {
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
    this.active = active;
    this.characteristics = characteristics;
    this.type = type;
    this.breed = breed;
    this.petPictures = petPictures;
    this.user = user;
    this.address = address;
    this.serialNumber = serialNumber;
    this.lastSeen = lastSeen;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Set<String> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(Set<String> characteristics) {
    this.characteristics = characteristics;
  }

  public AnimalType getType() {
    return type;
  }

  public void setType(AnimalType type) {
    this.type = type;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public List<Picture> getPetPictures() {
    return petPictures;
  }

  public void setPetPictures(List<Picture> petPictures) {
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

  public Date getLastSeen() {
    return lastSeen;
  }

  public void setLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }

  @Override
  public String toString() {
    return "Pet{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pet pet = (Pet) o;
    return Objects.equals(id, pet.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}