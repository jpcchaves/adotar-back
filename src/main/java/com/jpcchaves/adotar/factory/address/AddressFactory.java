package com.jpcchaves.adotar.factory.address;

import com.jpcchaves.adotar.domain.model.Address;

public interface AddressFactory {
    Address createUserEmptyAddress();

    Address createAddress(String zipcode,
                          String city,
                          String state);

    Address createAddress(String zipcode,
                          String street,
                          String number,
                          String complement,
                          String neighborhood,
                          String city,
                          String state);
}
