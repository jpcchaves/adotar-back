package com.jpcchaves.adotar.payload.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class AddressRequestDto {
    private Long id;
    @NotBlank(message = "O CEP é obrigatório")
    @Length(min = 8, max = 8, message = "CEP inválido")
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    @NotNull(message = "A cidade é obrigatória!")
    @Positive(message = "Dados inválidos, verifique os dados de cidade informados e tente novamente")
    private Long cityIbge;

    public AddressRequestDto() {
    }

    public AddressRequestDto(String zipcode,
                             String street,
                             String number,
                             String complement,
                             String neighborhood,
                             Long cityIbge) {
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.cityIbge = cityIbge;
    }

    public AddressRequestDto(Long id,
                             String zipcode,
                             String street,
                             String number,
                             String complement,
                             String neighborhood,
                             Long cityIbge) {
        this.id = id;
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.cityIbge = cityIbge;
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

    public Long getCityIbge() {
        return cityIbge;
    }

    public void setCityIbge(Long cityIbge) {
        this.cityIbge = cityIbge;
    }
}
