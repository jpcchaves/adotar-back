package com.jpcchaves.adotar.factory;

import com.jpcchaves.adotar.domain.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class ConcreteAddressFactory implements AddressFactory {

    @Override
    public Address createEmptyUserAddress() {
        return new Address("", "", "");
    }
}
