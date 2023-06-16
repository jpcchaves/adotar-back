package com.jpcchaves.adotar.payload.dto.pet;

public class PetPictureDto {
    private Long id;
    private String imgUrl;

    public PetPictureDto() {
    }

    public PetPictureDto(Long id,
                         String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
