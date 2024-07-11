package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;

import java.io.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_address", sequenceName = "seq_address", allocationSize = 1)
public abstract class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
  private Long id;

  @Column(length = 8, nullable = false)
  private String zipcode;

  @Column(length = 100, nullable = false)
  private String street;

  @Column(length = 50)
  private String number;

  @Column(length = 75, nullable = false)
  private String neighborhood;

  private String complement;

  @Column(nullable = false, length = 50)
  private String city;

  @Column(nullable = false, length = 50)
  private String state;

  public Address() {
  }

  public Address(
      Long id,
      String zipcode,
      String street,
      String number,
      String neighborhood,
      String complement,
      String city,
      String state
  ) {
    this.id = id;
    this.zipcode = zipcode;
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.complement = complement;
    this.city = city;
    this.state = state;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(id, address.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", zipcode='" + zipcode + '\'' +
        ", street='" + street + '\'' +
        ", number='" + number + '\'' +
        ", neighborhood='" + neighborhood + '\'' +
        ", complement='" + complement + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        '}';
  }
}
