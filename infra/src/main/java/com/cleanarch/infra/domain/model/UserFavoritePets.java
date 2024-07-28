package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import java.io.*;
import java.util.*;

@Entity
@Table(name = "user_favorite_pets")
@SequenceGenerator(
    name = "seq_user_favorite_pets",
    sequenceName = "seq_user_favorite_pets",
    allocationSize = 1)
public class UserFavoritePets implements Serializable {

  @Serial private static final long serialVersionUID = 3304814684570710897L;

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "seq_user_favorite_pets")
  private Long id;

  @ManyToOne(
      optional = false,
      targetEntity = User.class,
      fetch = FetchType.LAZY)
  @JoinColumn(
      name = "user_id",
      referencedColumnName = "id",
      nullable = false,
      foreignKey =
          @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT))
  private User user;

  @ManyToOne(
      optional = false,
      targetEntity = User.class,
      fetch = FetchType.LAZY)
  @JoinColumn(
      name = "pet_id",
      referencedColumnName = "id",
      nullable = false,
      foreignKey =
          @ForeignKey(name = "pet_fk", value = ConstraintMode.CONSTRAINT))
  private Pet pet;

  public UserFavoritePets() {}

  public UserFavoritePets(Long id, User user, Pet pet) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserFavoritePets that = (UserFavoritePets) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "UserFavoritePets{"
        + "id="
        + id
        + ", user="
        + user
        + ", pet="
        + pet
        + '}';
  }
}
