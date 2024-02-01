package com.jpcchaves.adotar.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet_characteristics")
public class PetCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true)
    private String characteristic;

    @ManyToMany(
            mappedBy = "characteristics"
    )
    private Set<Pet> pets = new HashSet<>();

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public PetCharacteristic() {
    }

    public PetCharacteristic(Long id,
                             String characteristic,
                             Date createdAt,
                             Date updatedAt) {
        this.id = id;
        this.characteristic = characteristic;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PetCharacteristic(Long id,
                             String characteristic,
                             Set<Pet> pets,
                             Date createdAt,
                             Date updatedAt) {
        this.id = id;
        this.characteristic = characteristic;
        this.pets = pets;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
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

    public Set<Pet> getPets() {
        return pets;
    }
}
