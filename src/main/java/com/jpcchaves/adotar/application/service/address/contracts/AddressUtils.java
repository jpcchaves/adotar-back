package com.jpcchaves.adotar.application.service.address.contracts;

import com.jpcchaves.adotar.application.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.City;

public interface AddressUtils {
  Address buildAddress(AddressRequestDto addressDto, City city);

  void updateAddress(Address address, City city, AddressRequestDto addressDto);
}
