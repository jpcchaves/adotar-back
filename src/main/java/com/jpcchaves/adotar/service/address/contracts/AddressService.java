package com.jpcchaves.adotar.service.address.contracts;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;

public interface AddressService {
    City fetchCityByIbge(Long cityIbge);

    Address createAddress(AddressRequestDto addressDto,
                          City city);
}
