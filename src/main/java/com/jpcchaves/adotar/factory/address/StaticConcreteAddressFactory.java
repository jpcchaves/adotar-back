package com.jpcchaves.adotar.factory.address;

import com.jpcchaves.adotar.application.dto.address.AddressResponseDto;

public class StaticConcreteAddressFactory {
    public static AddressResponseDto createAddressResponseDtoWithDefaultValues() {
        return new AddressResponseDto();
    }
}
