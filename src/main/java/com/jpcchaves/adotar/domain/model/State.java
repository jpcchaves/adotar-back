package com.jpcchaves.adotar.domain.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "states")
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60, nullable = false)
  private String name;

  @Column(length = 2, nullable = false)
  private String uf;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      mappedBy = "state")
  private List<City> cities = new ArrayList<>();

  public State() {}

  public State(Long id, String name, String uf, List<City> cities) {
    this.id = id;
    this.name = name;
    this.uf = uf;
    this.cities = cities;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }
}
