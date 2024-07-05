package com.cleanarch.usecase.common.dto;

public class PictureDTO {
  private Long id;
  private String fileName;
  private long size;
  private String type;
  private String imgUrl;

  public PictureDTO() {}

  public PictureDTO(
      Long id, String fileName, long size, String type, String imgUrl) {
    this.id = id;
    this.fileName = fileName;
    this.size = size;
    this.type = type;
    this.imgUrl = imgUrl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getfileName() {
    return fileName;
  }

  public void setfileName(String fileName) {
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
