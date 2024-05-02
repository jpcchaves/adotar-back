package com.jpcchaves.adotar.infrastructure.application.service.address;

import com.jpcchaves.adotar.infrastructure.application.service.address.contracts.AddressRepositoryService;
import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.model.Address;
import com.jpcchaves.adotar.infrastructure.domain.model.City;
import com.jpcchaves.adotar.infrastructure.infra.repository.AddressRepository;
import com.jpcchaves.adotar.infrastructure.infra.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressRepositoryServiceImpl implements AddressRepositoryService {
  private final CityRepository cityRepository;
  private final AddressRepository addressRepository;

  public AddressRepositoryServiceImpl(
      CityRepository cityRepository, AddressRepository addressRepository) {
    this.cityRepository = cityRepository;
    this.addressRepository = addressRepository;
  }

  @Override
  public City fetchCityByIbge(Long cityIbge) {
    return cityRepository
        .findCityByIbge(cityIbge)
        .orElseThrow(
            () -> new ResourceNotFoundException("Cidade não encontrada!"));
  }

  @Override
  public Address saveAddress(Address address) {
    return addressRepository.save(address);
  }

  @Override
  public City fetchCityByName(String name) {
    return cityRepository
        .findCityByNameIgnoreCase(name)
        .orElseThrow(
            () -> new ResourceNotFoundException("Cidade não encontrada!"));
  }
}
