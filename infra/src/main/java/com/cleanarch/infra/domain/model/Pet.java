package com.cleanarch.infra.domain.model;

import br.com.jpcchaves.core.domain.enums.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import org.hibernate.annotations.*;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "pets")
@SequenceGenerator(name = "seq_pet", sequenceName = "seq_pet", allocationSize = 1)
public class Pet implements Serializable {

  @Serial
  private static final long serialVersionUID = 3936582638524306317L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pet")
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private Integer yearsAge = 0;

  @Column(nullable = false)
  private Integer monthsAge = 0;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Enumerated(EnumType.STRING)
  private AnimalSize size;

  @Enumerated(EnumType.STRING)
  private HealthCondition healthCondition;

  @Enumerated(EnumType.STRING)
  private AnimalType type;

  @Column(length = 100)
  private String color;

  private String description;

  private Integer visualizations = 0;

  private Boolean isAvailable;

  @Temporal(TemporalType.DATE)
  private Date adoptionDate;

  private Boolean active;

  @Column(nullable = false, columnDefinition = "TEXT")
  private Set<String> characteristics = new HashSet<>();

  @Column(nullable = false, length = 100)
  private String breed;

  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;

  private Date deletedAt;

  public Pet() {
  }

  public Pet(
      Long id,
      String name,
      Integer yearsAge,
      Integer monthsAge,
      Gender gender,
      AnimalSize size,
      HealthCondition healthCondition,
      AnimalType type,
      String color,
      String description,
      Integer visualizations,
      Boolean isAvailable,
      Date adoptionDate,
      Boolean active,
      Set<String> characteristics,
      String breed,
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
    this.type = type;
    this.color = color;
    this.description = description;
    this.visualizations = visualizations;
    this.isAvailable = isAvailable;
    this.adoptionDate = adoptionDate;
    this.active = active;
    this.characteristics = characteristics;
    this.breed = breed;
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

  public Integer getYearsAge() {
    return yearsAge;
  }

  public void setYearsAge(Integer yearsAge) {
    this.yearsAge = yearsAge;
  }

  public Integer getMonthsAge() {
    return monthsAge;
  }

  public void setMonthsAge(Integer monthsAge) {
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

  public AnimalType getType() {
    return type;
  }

  public void setType(AnimalType type) {
    this.type = type;
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

  public Integer getVisualizations() {
    return visualizations;
  }

  public void setVisualizations(Integer visualizations) {
    this.visualizations = visualizations;
  }

  public Boolean getAvailable() {
    return isAvailable;
  }

  public void setAvailable(Boolean available) {
    isAvailable = available;
  }

  public Date getAdoptionDate() {
    return adoptionDate;
  }

  public void setAdoptionDate(Date adoptionDate) {
    this.adoptionDate = adoptionDate;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Set<String> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(Set<String> characteristics) {
    this.characteristics = characteristics;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
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
}
