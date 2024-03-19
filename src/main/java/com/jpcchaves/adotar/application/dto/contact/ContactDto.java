package com.jpcchaves.adotar.application.dto.contact;

public class ContactDto {
  private Long id;
  private String phone1;
  private String phone2;
  private String phone3;

  public ContactDto() {}

  public ContactDto(Long id, String phone1, String phone2, String phone3) {
    this.id = id;
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
