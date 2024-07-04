package br.com.jpcchaves.core.domain.model;

public class PetPicture extends Picture {
  private Pet pet;

  public PetPicture() {
  }

  public PetPicture(
      Long id,
      String name,
      long size,
      String type,
      String imgUrl,
      Pet pet
  ) {
    super(id, name, size, type, imgUrl);
    this.pet = pet;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }
}
