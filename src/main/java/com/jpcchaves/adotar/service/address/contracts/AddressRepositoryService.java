package com.jpcchaves.adotar.service.address.contracts;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;

public interface AddressRepositoryService {
    City fetchCityByIbge(Long cityIbge);

    Address saveAddress(Address address);

    City fetchCityByName(String name);

}
