package com.cleanarch.usecase.common.dto;

public class AddressMinDTO {
  private String state;
  private String city;

  public AddressMinDTO() {}

  public AddressMinDTO(String state, String city) {
    this.state = state;
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
