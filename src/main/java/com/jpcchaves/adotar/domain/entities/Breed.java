package com.jpcchaves.adotar.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "breeds")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "animal_type_id",
            referencedColumnName = "id"
    )
    private AnimalType animalType;

    public Breed() {
    }

    public Breed(Long id,
                 String name,
                 AnimalType animalType) {
        this.id = id;
        this.name = name;
        this.animalType = animalType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
