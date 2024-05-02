package com.jpcchaves.adotar.infrastructure.domain.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class PetPicture {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 50)
  private long size;

  @Column(nullable = false, length = 50)
  private String type;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String imgUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pet_id")
  private Pet pet;

  public PetPicture() {}

  public PetPicture(
      UUID id, String name, long size, String type, String imgUrl) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.type = type;
    this.imgUrl = imgUrl;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }
}
