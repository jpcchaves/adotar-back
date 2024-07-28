package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import java.io.*;

@Entity
@Table(name = "pet_picture")
public class PetPicture extends Picture {

  @Serial private static final long serialVersionUID = 1873429527284615649L;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pet.class, optional = false)
  @JoinColumn(
      name = "pet_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey =
          @ForeignKey(name = "pet_fk", value = ConstraintMode.CONSTRAINT))
  private Pet pet;

  public PetPicture() {}

  public PetPicture(
      Long id,
      String fileName,
      long size,
      String type,
      String imgUrl,
      Pet pet) {
    super(id, fileName, size, type, imgUrl);
    this.pet = pet;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  @Override
  public String toString() {
    return "PetPicture{" + "pet=" + pet + '}';
  }
}
