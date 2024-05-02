package com.jpcchaves.adotar.infrastructure.application.service.address.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.infrastructure.domain.model.Address;
import com.jpcchaves.adotar.infrastructure.domain.model.City;

public interface AddressUtils {
  Address buildAddress(AddressRequestDto addressDto, City city);

  void updateAddress(Address address, City city, AddressRequestDto addressDto);
}
