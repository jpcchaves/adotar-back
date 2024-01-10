package com.jpcchaves.adotar.service.address.contracts;

import com.jpcchaves.adotar.domain.entities.City;

public interface AddressRepositoryService {
    City fetchCityByIbge(Long cityIbge);
}
