package br.com.jpcchaves.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String photoUrl;
  private String password;
  private boolean isAdmin;
  private boolean isActive;
  private Set<Role> roles = new HashSet<>();
  private List<Pet> pets = new ArrayList<>();
  private Address address;
  private Contact contact;
  private Date lastSeen;
  private Date createdAt;
  private Date updatedAt;
  private Date deletedAt;

  public User(String firstName, String lastName, String userName, String email,
      String photoUrl,
      String password, boolean isAdmin, boolean isActive, Set<Role> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.photoUrl = photoUrl;
    this.password = password;
    this.isAdmin = isAdmin;
    this.isActive = isActive;
    this.roles = roles;
  }

  public User(String firstName, String lastName, String userName, String email,
      String photoUrl,
      String password, boolean isAdmin, boolean isActive, Set<Role> roles,
      List<Pet> pets,
      Address address, Contact contact) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.photoUrl = photoUrl;
    this.password = password;
    this.isAdmin = isAdmin;
    this.isActive = isActive;
    this.roles = roles;
    this.pets = pets;
    this.address = address;
    this.contact = contact;
    this.createdAt = new Date();
  }

  public User(Long id, String firstName, String lastName, String userName,
      String email,
      String photoUrl, String password, boolean isAdmin, boolean isActive,
      Set<Role> roles,
      List<Pet> pets, Address address, Contact contact, Date lastSeen,
      Date createdAt,
      Date updatedAt,
      Date deletedAt) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.photoUrl = photoUrl;
    this.password = password;
    this.isAdmin = isAdmin;
    this.isActive = isActive;
    this.roles = roles;
    this.pets = pets;
    this.address = address;
    this.contact = contact;
    this.lastSeen = lastSeen;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Date getLastSeen() {
    return lastSeen;
  }

  public void setLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }
}
