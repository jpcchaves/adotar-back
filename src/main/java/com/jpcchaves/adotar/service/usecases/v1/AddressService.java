package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;

public interface AddressService {
    AddressDto getUserAddress();

    AddressDto updateUserAddress(AddressRequestDto addressDto);

    ApiMessageResponseDto createAddress(AddressRequestDto addressDto);
}
