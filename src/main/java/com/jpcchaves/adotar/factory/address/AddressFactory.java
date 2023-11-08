package com.jpcchaves.adotar.factory.address;

import com.jpcchaves.adotar.domain.entities.Address;

public interface AddressFactory {
    Address createUserEmptyAddress();
    Address createAddress(String zipcode, String city, String state);
}
