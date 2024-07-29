package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import java.io.*;

@Entity
@Table(name = "user_address")
public class UserAddress extends Address {
  @Serial private static final long serialVersionUID = 4302061964775105262L;

  @OneToOne(
      fetch = FetchType.LAZY,
      optional = false,
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      targetEntity = User.class)
  @JoinColumn(
      name = "user_id",
      nullable = false,
      unique = true,
      referencedColumnName = "id",
      foreignKey =
          @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT))
  private User user;

  public UserAddress() {}

  public UserAddress(
      Long id,
      String zipcode,
      String street,
      String number,
      String neighborhood,
      String complement,
      String city,
      String state,
      User user) {
    super(id, zipcode, street, number, neighborhood, complement, city, state);
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserAddress{" + "user=" + user + '}';
  }
}
