package br.com.jpcchaves.core.domain.model;

import java.util.*;

// This is going to be a @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Picture {

  private Long id;
  private String fileName;
  private long size;
  private String type;
  private String imgUrl;

  public Picture() {
  }

  public Picture(
      Long id,
      String fileName,
      long size,
      String type,
      String imgUrl
  ) {
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

  public String getName() {
    return fileName;
  }

  public void setName(String fileName) {
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

  @Override
  public String toString() {
    return "Picture{" +
        "id=" + id +
        ", fileName='" + fileName + '\'' +
        ", size=" + size +
        ", type='" + type + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Picture picture = (Picture) o;
    return Objects.equals(id, picture.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
