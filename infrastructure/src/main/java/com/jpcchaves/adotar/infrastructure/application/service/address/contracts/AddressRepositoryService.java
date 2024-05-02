package com.jpcchaves.adotar.infrastructure.application.service.address.contracts;

import com.jpcchaves.adotar.infrastructure.domain.model.Address;
import com.jpcchaves.adotar.infrastructure.domain.model.City;

public interface AddressRepositoryService {
  City fetchCityByIbge(Long cityIbge);

  Address saveAddress(Address address);

  City fetchCityByName(String name);
}
