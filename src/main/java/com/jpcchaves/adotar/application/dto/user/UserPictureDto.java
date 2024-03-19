package com.jpcchaves.adotar.application.dto.user;

import com.jpcchaves.adotar.domain.annotations.base64validator.ValidBase64;

public class UserPictureDto {
  @ValidBase64 private String pictureUrl;

  public UserPictureDto() {}

  public UserPictureDto(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }
}
