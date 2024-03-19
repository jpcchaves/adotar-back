package com.jpcchaves.adotar.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 11)
  private String phone1;

  @Column(length = 11)
  private String phone2;

  @Column(length = 11)
  private String phone3;

  public Contact() {}

  public Contact(Long id, String phone1, String phone2, String phone3) {
    this.id = id;
    this.phone1 = phone1;
    this.phone2 = phone2;
    this.phone3 = phone3;
  }

  public Contact(String phone1, String phone2, String phone3) {
    this.phone1 = phone1;
    this.phone2 = phone2;
    this.phone3 = phone3;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPhone1() {
    return phone1;
  }

  public void setPhone1(String phone1) {
    this.phone1 = phone1;
  }

  public String getPhone2() {
    return phone2;
  }

  public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }

  public String getPhone3() {
    return phone3;
  }

  public void setPhone3(String phone3) {
    this.phone3 = phone3;
  }
}
