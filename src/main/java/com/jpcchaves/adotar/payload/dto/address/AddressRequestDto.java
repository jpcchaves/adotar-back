package com.jpcchaves.adotar.payload.dto.address;

public class AddressRequestDto {
    private Long id;
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private Long cityId;

    public AddressRequestDto() {
    }

    public AddressRequestDto(Long id,
                             String zipcode,
                             String street,
                             String number,
                             String complement,
                             String neighborhood,
                             Long cityId) {
        this.id = id;
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.cityId = cityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
