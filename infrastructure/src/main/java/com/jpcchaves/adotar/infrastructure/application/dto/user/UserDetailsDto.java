package com.jpcchaves.adotar.infrastructure.application.dto.user;

import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.contact.ContactDto;
import java.util.Date;

public class UserDetailsDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String name;
  private String email;
  private ContactDto contact;
  private AddressResponseDto address;
  private Date lastSeen;
  private Date updatedAt;
  private Date createdAt;

  public UserDetailsDto() {}

  public UserDetailsDto(
      Long id,
      String firstName,
      String lastName,
      String username,
      String name,
      String email,
      ContactDto contact,
      AddressResponseDto address,
      Date lastSeen,
      Date updatedAt,
      Date createdAt) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.name = name;
    this.email = email;
    this.contact = contact;
    this.address = address;
    this.lastSeen = lastSeen;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ContactDto getContact() {
    return contact;
  }

  public void setContact(ContactDto contact) {
    this.contact = contact;
  }

  public AddressResponseDto getAddress() {
    return address;
  }

  public void setAddress(AddressResponseDto address) {
    this.address = address;
  }

  public Date getLastSeen() {
    return lastSeen;
  }

  public void setLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
