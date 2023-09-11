package com.jpcchaves.adotar.domain.entities;

import jakarta.persistence.*;

@Entity
@Table
public class PetPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String imgUrl;

    @Column(nullable = false)
    private boolean isFavorite = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public PetPicture() {
    }

    public PetPicture(
            Long id,
            String imgUrl,
            boolean isFavorite,
            Pet pet) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.isFavorite = isFavorite;
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
