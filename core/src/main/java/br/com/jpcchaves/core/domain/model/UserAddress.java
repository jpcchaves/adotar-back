package br.com.jpcchaves.core.domain.model;

public class UserAddress extends Address {
  private User user;

  public UserAddress(
      Long id,
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String state,
      User user) {
    super(id, zipcode, street, number, complement, neighborhood, city, state);
    this.user = user;
  }

  public UserAddress(
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String state,
      User user) {
    super(zipcode, street, number, complement, neighborhood, city, state);
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
