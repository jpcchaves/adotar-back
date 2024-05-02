package com.jpcchaves.adotar.infrastructure.factory.address;

import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.infrastructure.domain.model.Address;

public interface AddressFactory {
  Address createUserEmptyAddress();

  Address createAddress(String zipcode, String city, String state);

  Address createAddress(
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String state);

  AddressResponseDto createAddressResponseDto(
      String zipcode,
      String street,
      String number,
      String complement,
      String neighborhood,
      String city,
      String cityName,
      String state,
      String stateName);
}
