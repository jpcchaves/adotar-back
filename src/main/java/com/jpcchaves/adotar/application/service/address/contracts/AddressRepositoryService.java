package com.jpcchaves.adotar.application.service.address.contracts;

import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.City;

public interface AddressRepositoryService {
  City fetchCityByIbge(Long cityIbge);

  Address saveAddress(Address address);

  City fetchCityByName(String name);
}
