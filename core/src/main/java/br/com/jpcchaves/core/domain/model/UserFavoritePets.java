package br.com.jpcchaves.core.domain.model;

public class UserFavoritePets {

  private Long id;
  private User user;
  private Pet pet;

  public UserFavoritePets() {
  }

  public UserFavoritePets(
      Long id,
      User user,
      Pet pet
  ) {
    this.id = id;
    this.user = user;
    this.pet = pet;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }
}
