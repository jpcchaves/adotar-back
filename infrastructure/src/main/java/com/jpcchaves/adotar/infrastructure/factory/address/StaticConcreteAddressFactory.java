package com.jpcchaves.adotar.infrastructure.factory.address;

import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressResponseDto;

public class StaticConcreteAddressFactory {
  public static AddressResponseDto createAddressResponseDtoWithDefaultValues() {
    return new AddressResponseDto();
  }
}
