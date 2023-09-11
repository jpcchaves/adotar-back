package com.jpcchaves.adotar.payload.dto.pet;

import com.jpcchaves.adotar.domain.annotations.base64validator.ValidBase64;

public class PetPictureDto {
    private Long id;
    @ValidBase64
    private String imgUrl;
    private boolean isFavorite;

    public PetPictureDto() {
    }

    public PetPictureDto(
            Long id,
            String imgUrl,
            boolean isFavorite) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.isFavorite = isFavorite;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
