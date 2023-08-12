package com.jpcchaves.adotar.factory.address;

import com.jpcchaves.adotar.domain.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class ConcreteAddressFactory implements AddressFactory {

    @Override
    public Address createUserEmptyAddress() {
        return new Address("", "", "");
    }
}
