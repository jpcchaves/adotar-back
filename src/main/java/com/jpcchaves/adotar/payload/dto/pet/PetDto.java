package com.jpcchaves.adotar.payload.dto.pet;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PetDto {
    private Long id;
    private String name;
    private int yearsAge;
    private int monthsAge;
    private char gender;
    private String color;
    private String description;
    private int visualizations;
    private boolean isAvailable;
    private Date adoptionDate;
    private boolean active;
    private Set<PetCharacteristicsDto> characteristics = new HashSet<>();
    private String type;

    public PetDto() {
    }

    public PetDto(Long id,
                  String name,
                  int yearsAge,
                  int monthsAge,
                  char gender,
                  String color,
                  String description,
                  int visualizations,
                  boolean active,
                  boolean isAvailable,
                  Date adoptionDate,
                  Set<PetCharacteristicsDto> characteristics,
                  AnimalTypeDto type) {
        this.id = id;
        this.name = name;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        this.gender = gender;
        this.color = color;
        this.description = description;
        this.visualizations = visualizations;
        this.active = active;
        this.adoptionDate = adoptionDate;
        this.isAvailable = isAvailable;
        this.characteristics = characteristics;
        this.type = type.getType();
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
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

    public Set<PetCharacteristicsDto> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<PetCharacteristicsDto> characteristics) {
        this.characteristics = characteristics;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
