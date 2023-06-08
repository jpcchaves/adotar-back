package com.jpcchaves.adotar.domain.entities;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 2)
    private int yearsAge;
    @Column(length = 2)
    private int monthsAge;
    @Column(length = 6)
    private char gender;
    @Column(length = 30)
    private String color;
    private String description;
    private boolean active;

    @ManyToMany(
            cascade = {CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "pets_characteristics",
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
