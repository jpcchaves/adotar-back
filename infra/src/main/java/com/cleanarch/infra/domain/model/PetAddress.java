package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import java.io.*;

@Entity
@Table(name = "pet_address")
public class PetAddress extends Address {
  @Serial private static final long serialVersionUID = -7170105759657703108L;

  @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Pet.class)
  @JoinColumn(
      name = "pet_id",
      nullable = false,
      unique = true,
      foreignKey =
          @ForeignKey(name = "pet_fk", value = ConstraintMode.CONSTRAINT))
  private Pet pet;

  public PetAddress() {}

  public PetAddress(
      Long id,
      String zipcode,
      String street,
      String number,
      String neighborhood,
      String complement,
      String city,
      String state,
      Pet pet) {
    super(id, zipcode, street, number, neighborhood, complement, city, state);
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
    return "PetAddress{" + "pet=" + pet + '}';
  }
}
