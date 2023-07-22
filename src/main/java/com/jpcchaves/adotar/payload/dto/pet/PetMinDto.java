package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.payload.dto.address.AddressMinDto;

import java.util.ArrayList;
import java.util.List;

public class PetMinDto {
    private Long id;
    private String name;
    private char gender;
    private int visualizations;
    private String type;
    private String breed;
    private List<PetPictureDto> petPictures = new ArrayList<>();
    private AddressMinDto address;

    public PetMinDto() {
    }

    public PetMinDto(Long id,
                     String name,
                     char gender,
                     int visualizations,
                     String type,
                     String breed,
                     List<PetPictureDto> petPictures,
                     AddressMinDto address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.visualizations = visualizations;
        this.type = type;
        this.breed = breed;
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

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<PetPictureDto> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<PetPictureDto> petPictures) {
        this.petPictures = petPictures;
    }

    public AddressMinDto getAddress() {
        return address;
    }

    public void setAddress(AddressMinDto address) {
        this.address = address;
    }
}