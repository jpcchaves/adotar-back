package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.address.AddressDto;

public interface AddressService {
    AddressDto getUserAddress();
    
    AddressDto updateUserAddress(AddressDto addressDto);
}
