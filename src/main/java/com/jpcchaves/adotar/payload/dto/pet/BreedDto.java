package com.jpcchaves.adotar.payload.dto.pet;

public class BreedDto {
    private Long id;
    private String name;

    public BreedDto() {
    }

    public BreedDto(Long id,
                    String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
