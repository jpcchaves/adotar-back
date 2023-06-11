package com.jpcchaves.adotar.payload.dto.pet;

import java.util.HashSet;
import java.util.Set;

public class AnimalTypeDto {
    private Long id;
    private String type;
    private Set<BreedDto> breeds = new HashSet<>();

    public AnimalTypeDto() {
    }

    public AnimalTypeDto(Long id,
                         String type,
                         Set<BreedDto> breeds) {
        this.id = id;
        this.type = type;
        this.breeds = breeds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<BreedDto> getBreeds() {
        return breeds;
    }

    public void setBreeds(Set<BreedDto> breeds) {
        this.breeds = breeds;
    }
}
