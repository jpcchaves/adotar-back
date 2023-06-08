package com.jpcchaves.adotar.domain.entities;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Pet {
    private Long id;
    private String name;
    private int yearsAge;
    private int monthsAge;
    private char gender;
    private String color;
    private String description;
    private boolean active;

    @ManyToMany(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "pets_characteristic",
            joinColumns = @JoinColumn(
                    name = "pet_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "characteristic_id", referencedColumnName = "id"
            )
    )
    private Set<PetCharacteristic> characteristics = new HashSet<>();

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


    public AnimalGender getGender() {
        return AnimalGender.valueOf(gender);
    }

    public void setGender(AnimalGender gender) {
        if (gender != null) {
            this.gender = gender.getGender();
        }
    }
}
