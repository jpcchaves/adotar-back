package com.jpcchaves.adotar.factory.address;

import com.jpcchaves.adotar.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.domain.model.Address;
import org.springframework.stereotype.Component;

@Component
public class ConcreteAddressFactory implements AddressFactory {

    @Override
    public Address createUserEmptyAddress() {
        return new Address("", "", "");
    }

    @Override
    public Address createAddress(String zipcode,
                                 String city,
                                 String state) {
        return new Address(zipcode, city, state);
    }

    @Override
    public Address createAddress(String zipcode,
                                 String street,
                                 String number,
                                 String complement,
                                 String neighborhood,
                                 String city,
                                 String state) {
        return new Address(zipcode, street, number, complement, neighborhood, city, state);
    }

    @Override
    public AddressResponseDto createAddressResponseDto(String zipcode,
                                                       String street,
                                                       String number,
                                                       String complement,
                                                       String neighborhood,
                                                       String city,
                                                       String cityName,
                                                       String state,
                                                       String stateName) {
        return new AddressResponseDto(
                zipcode,
                street,
                number,
                complement,
                neighborhood,
                city,
                cityName,
                state,
                stateName
        );
    }
}
