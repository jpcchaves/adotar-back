package com.jpcchaves.adotar.application.dto.pet;

public class PetPictureMinDto {
  private String name;
  private long size;
  private String type;
  private String imgUrl;

  public PetPictureMinDto() {}

  public PetPictureMinDto(String name, long size, String type, String imgUrl) {
    this.name = name;
    this.size = size;
    this.type = type;
    this.imgUrl = imgUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
