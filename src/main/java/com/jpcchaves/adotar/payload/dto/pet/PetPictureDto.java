package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.domain.annotations.base64validator.ValidBase64;

public class PetPictureDto {
    private Long id;
    @ValidBase64
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
