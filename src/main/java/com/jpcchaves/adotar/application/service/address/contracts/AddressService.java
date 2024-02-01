package com.jpcchaves.adotar.application.service.address.contracts;

import com.jpcchaves.adotar.application.dto.address.AddressDto;
import com.jpcchaves.adotar.application.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.City;

public interface AddressService {
    City fetchCityByIbge(Long cityIbge);

    City fetchCityByName(String name);

    Address buildAddress(AddressRequestDto addressDto,
                         City city);

    AddressDto getUserAddress();

    AddressDto updateUserAddress(AddressRequestDto addressDto);

    ApiMessageResponseDto createAddress(AddressRequestDto addressDto);

}
