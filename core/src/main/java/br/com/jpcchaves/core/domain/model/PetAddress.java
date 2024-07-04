package br.com.jpcchaves.core.domain.model;

public class PetAddress extends Address {
  private Pet pet;

  public PetAddress(
      Long id,
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String state,
      Pet pet) {
    super(id, zipcode, street, number, complement, neighborhood, city, state);
    this.pet = pet;
  }

  public PetAddress(
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String state,
      Pet pet) {
    super(zipcode, street, number, complement, neighborhood, city, state);
    this.pet = pet;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }
}
