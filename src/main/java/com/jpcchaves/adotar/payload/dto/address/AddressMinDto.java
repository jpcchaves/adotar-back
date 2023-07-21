package com.jpcchaves.adotar.payload.dto.address;

public class AddressMinDto {
    private String state;
    private String city;

    public AddressMinDto() {
    }

    public AddressMinDto(String state,
                         String city) {
        this.state = state;
        this.city = city;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
