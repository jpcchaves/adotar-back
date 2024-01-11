package com.jpcchaves.adotar.service.address.contracts;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;

public interface AddressService {
    City fetchCityByIbge(Long cityIbge);

    Address buildAddress(AddressRequestDto addressDto,
                         City city);

    AddressDto getUserAddress();

    AddressDto updateUserAddress(AddressRequestDto addressDto);

    ApiMessageResponseDto createAddress(AddressRequestDto addressDto);

}
