package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(
    name = "seq_picture",
    sequenceName = "seq_picture",
    allocationSize = 1)
public abstract class Picture implements Serializable {

  @Serial private static final long serialVersionUID = 4194788856706835259L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_picture")
  private Long id;

  private String fileName;

  private long size;

  private String type;

  private String imgUrl;

  public Picture() {}

  public Picture(
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

  @Override
  public String toString() {
    return "Picture{"
        + "id="
        + id
        + ", fileName='"
        + fileName
        + '\''
        + ", size="
        + size
        + ", type='"
        + type
        + '\''
        + ", imgUrl='"
        + imgUrl
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Picture picture = (Picture) o;
    return Objects.equals(id, picture.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
