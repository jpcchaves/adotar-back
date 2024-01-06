package com.jpcchaves.adotar.payload.dto.pet;

public class PetPictureCreateDto {
    private String imgUrl;

    public PetPictureCreateDto() {
    }

    public PetPictureCreateDto(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
