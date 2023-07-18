package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;

public interface AddressService {
    AddressDto getUserAddress();

    AddressDto updateUserAddress(AddressRequestDto addressDto);
}
