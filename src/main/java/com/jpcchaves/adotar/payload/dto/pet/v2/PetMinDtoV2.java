package com.jpcchaves.adotar.payload.dto.pet.v2;

import com.jpcchaves.adotar.domain.entities.Breed;
import com.jpcchaves.adotar.payload.dto.address.AddressMinDto;
import com.jpcchaves.adotar.payload.dto.pet.BreedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;

import java.util.ArrayList;
import java.util.List;

public class PetMinDtoV2 {
    private Long id;
    private String name;
    private String description;
    private char gender;
    private int visualizations;
    private String type;
    private String breed;
    private boolean isFavorite;
    private List<String> petPictures = new ArrayList<>();
    private AddressMinDto address;

    public PetMinDtoV2() {
    }

    public PetMinDtoV2(
            Long id,
            String name,
            String description,
            char gender,
            int visualizations,
            String type,
            BreedDto breed,
            boolean isFavorite,
            List<String> petPictures,
            AddressMinDto address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.visualizations = visualizations;
        this.type = type;
        this.breed = breed.getName();
        this.isFavorite = isFavorite;
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

    public void setBreed(Breed breed) {
        this.breed = breed.getName();
    }

    public List<String> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<String> petPictures) {
        this.petPictures = petPictures;
    }

    public AddressMinDto getAddress() {
        return address;
    }

    public void setAddress(AddressMinDto address) {
        this.address = address;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
