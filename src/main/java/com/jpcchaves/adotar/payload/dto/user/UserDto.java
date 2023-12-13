package com.jpcchaves.adotar.payload.dto.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String username;
    private String email;
    private Boolean isAdmin;
    private Boolean isActive;
    private String photoUrl;
    private Set<String> roles = new HashSet<>();

    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public UserDto() {
    }

    public UserDto(
            Long id,
            String firstName,
            String lastName,
            String name,
            String username,
            String email,
            Boolean isAdmin,
            Boolean isActive,
            String role,
            String photoUrl,
            Set<String> roles,
            Date createdAt,
            Date updatedAt,
            Date deletedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = photoUrl;
        this.name = firstName + " " + lastName;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
        this.roles = roles;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
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
