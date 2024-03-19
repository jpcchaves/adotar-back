package com.jpcchaves.adotar.factory.address;

import com.jpcchaves.adotar.application.dto.address.AddressResponseDto;

public abstract class StaticAbstractAddressFactory {
  public static AddressResponseDto createAddressResponseDtoWithDefaultValues() {
    return new AddressResponseDto();
  }
}
