package com.jpcchaves.adotar.infrastructure.application.service.address.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressDto;
import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.infrastructure.domain.model.Address;
import com.jpcchaves.adotar.infrastructure.domain.model.City;

public interface AddressService {
  City fetchCityByIbge(Long cityIbge);

  City fetchCityByName(String name);

  Address buildAddress(AddressRequestDto addressDto, City city);

  AddressDto getUserAddress();

  AddressDto updateUserAddress(AddressRequestDto addressDto);

  ApiMessageResponseDto createAddress(AddressRequestDto addressDto);
}
