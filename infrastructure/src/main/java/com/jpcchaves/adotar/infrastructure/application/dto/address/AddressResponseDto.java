package com.jpcchaves.adotar.infrastructure.application.dto.address;

public class AddressResponseDto {
  private String zipcode;
  private String street;
  private String number;
  private String complement;
  private String neighborhood;
  private String city;
  private String cityName;
  private String state;
  private String stateName;

  public AddressResponseDto() {}

  public AddressResponseDto(
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String cityName,
      String state,
      String stateName) {
    this.zipcode = zipcode;
    this.street = street;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.city = city;
    this.cityName = cityName;
    this.state = state;
    this.stateName = stateName;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }
}
