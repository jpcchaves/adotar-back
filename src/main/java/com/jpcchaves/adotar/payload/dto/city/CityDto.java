package com.jpcchaves.adotar.payload.dto.city;

public class CityDto {
    private Long id;
    private String name;
    private int ibge;

    public CityDto() {
    }

    public CityDto(Long id,
                   String name, int ibge) {
        this.id = id;
        this.name = name;
        this.ibge = ibge;
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

    public int getIbge() {
        return ibge;
    }
    public void setIbge(int ibge) {
        this.ibge = ibge;
    }
}
