package com.jpcchaves.adotar.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 120)
  private String name;

  @Column(length = 10, nullable = false, unique = true)
  private Long ibge;

  private Double latitude;
  private Double longitude;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "uf_id")
  private State state;

  public City() {}

  public City(
      Long id,
      String name,
      Long ibge,
      Double latitude,
      Double longitude,
      State state) {
    this.id = id;
    this.name = name;
    this.ibge = ibge;
    this.latitude = latitude;
    this.longitude = longitude;
    this.state = state;
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

  public Long getIbge() {
    return ibge;
  }

  public void setIbge(Long ibge) {
    this.ibge = ibge;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }
}
