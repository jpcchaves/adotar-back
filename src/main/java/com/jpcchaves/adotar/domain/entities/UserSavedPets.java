package com.jpcchaves.adotar.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_saved_pets")
public class UserSavedPets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public UserSavedPets() {
    }

    public UserSavedPets(User user, Pet pet) {
        this.user = user;
        this.pet = pet;
    }

    public UserSavedPets(Long id, User user, Pet pet) {
        this.id = id;
        this.user = user;
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
