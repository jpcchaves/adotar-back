package com.jpcchaves.adotar.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pet_characteristics")
public class PetCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true)
    private String characteristic;

    public PetCharacteristic() {
    }

    public PetCharacteristic(Long id,
                             String characteristic) {
        this.id = id;
        this.characteristic = characteristic;
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
}
