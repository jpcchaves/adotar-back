package com.jpcchaves.adotar.infrastructure.factory.address;

import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressResponseDto;

public abstract class StaticAbstractAddressFactory {
  public static AddressResponseDto createAddressResponseDtoWithDefaultValues() {
    return new AddressResponseDto();
  }
}
