package com.jpcchaves.adotar.domain.entities;

import com.jpcchaves.adotar.domain.Enum.AnimalGender;
import com.jpcchaves.adotar.domain.Enum.AnimalSize;
import com.jpcchaves.adotar.domain.Enum.HealthCondition;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

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
    @Enumerated(EnumType.STRING)
    private AnimalGender gender;

    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    private AnimalSize size;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private HealthCondition healthCondition;

    @Column(length = 30)
    private String color;

    @Column(length = 100)
    private String description;

    @Column(length = 10)
    private int visualizations;

    private boolean isAvailable;

    private Date adoptionDate;

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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(
            name = "type_id", referencedColumnName = "id"
    )
    private AnimalType type;

    // CHECKED
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "breed_id",
            referencedColumnName = "id"
    )
    private Breed breed;

    // CHECKED
    @OneToMany(
            mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PetPicture> petPictures = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id"
    )
    private Address address;

    @Column(length = 25, unique = true, nullable = false)
    private String serialNumber;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private Date deletedAt;

    public Pet() {
    }

    public Pet(Long id,
               String name,
               int yearsAge,
               int monthsAge,
               AnimalGender gender,
               AnimalSize size,
               HealthCondition healthCondition,
               String color,
               String description,
               int visualizations,
               boolean active,
               boolean isAvaiable,
               Date adoptionDate,
               Set<PetCharacteristic> characteristics,
               AnimalType type,
               Breed breed,
               List<PetPicture> petPictures,
               User user,
               Address address,
               String serialNumber, Date createdAt,
               Date updatedAt,
               Date deletedAt) {
        this.id = id;
        this.name = name;
        this.yearsAge = yearsAge;
        this.monthsAge = monthsAge;
        this.gender = gender;
        this.healthCondition = healthCondition;
        this.size = size;
        this.color = color;
        this.description = description;
        this.visualizations = visualizations;
        this.active = active;
        this.isAvailable = isAvaiable;
        this.adoptionDate = adoptionDate;
        this.characteristics = characteristics;
        this.type = type;
        this.breed = breed;
        this.petPictures = petPictures;
        this.user = user;
        this.address = address;
        this.serialNumber = serialNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsAge() {
        return yearsAge;
    }

    public void setYearsAge(int yearsAge) {
        this.yearsAge = yearsAge;
    }

    public int getMonthsAge() {
        return monthsAge;
    }

    public void setMonthsAge(int monthsAge) {
        this.monthsAge = monthsAge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<PetCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<PetCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public AnimalGender getGender() {
        return gender;
    }

    public void setGender(AnimalGender gender) {
        this.gender = gender;
    }

    public AnimalSize getSize() {
        return size;
    }

    public void setSize(AnimalSize size) {
        this.size = size;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(HealthCondition healthCondition) {
        this.healthCondition = healthCondition;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public int getVisualizations() {
        return visualizations;
    }

    public void setVisualizations(int visualizations) {
        this.visualizations = visualizations;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(Date adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public List<PetPicture> getPetPictures() {
        return petPictures;
    }

    public void setPetPictures(List<PetPicture> petPictures) {
        this.petPictures = petPictures;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
