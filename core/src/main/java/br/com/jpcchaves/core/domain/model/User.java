package br.com.jpcchaves.core.domain.model;

import java.util.*;

public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Picture picture;
  private String password;
  private boolean isAdmin;
  private boolean isActive;
  private Set<Role> roles = new HashSet<>();
  private Address address;
  private String phone;
  private String phone2;
  private Date lastSeen;
  private Date createdAt;
  private Date updatedAt;
  private Date deletedAt;

  public User() {
  }

  public User(
      Long id,
      String firstName,
      String lastName,
      String email,
      Picture picture,
      String password,
      boolean isAdmin,
      boolean isActive,
      Set<Role> roles,
      Address address,
      String phone,
      String phone2,
      Date lastSeen,
      Date createdAt,
      Date updatedAt,
      Date deletedAt
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.picture = picture;
    this.password = password;
    this.isAdmin = isAdmin;
    this.isActive = isActive;
    this.roles = roles;
    this.address = address;
    this.phone = phone;
    this.phone2 = phone2;
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
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Picture getPhotoUrl() {
    return picture;
  }

  public void setPhotoUrl(Picture picture) {
    this.picture = picture;
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone2() {
    return phone2;
  }

  public void setPhone2(String phone2) {
    this.phone2 = phone2;
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

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
