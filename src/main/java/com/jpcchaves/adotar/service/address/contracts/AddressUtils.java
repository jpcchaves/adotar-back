package com.jpcchaves.adotar.service.address.contracts;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;

public interface AddressUtils {
    Address createAddress(
            AddressRequestDto addressDto,
            City city
    );
}
