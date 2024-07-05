package com.cleanarch.usecase.common.dto;

public class PetPictureMinDTO {
  private String fileName;
  private long size;
  private String type;
  private String imgUrl;

  public PetPictureMinDTO() {}

  public PetPictureMinDTO(
      String fileName, long size, String type, String imgUrl) {
    this.fileName = fileName;
    this.size = size;
    this.type = type;
    this.imgUrl = imgUrl;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
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
