package com.jpcchaves.adotar.application.service.address;

import com.jpcchaves.adotar.application.service.address.contracts.AddressRepositoryService;
import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.City;
import com.jpcchaves.adotar.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infra.repository.AddressRepository;
import com.jpcchaves.adotar.infra.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressRepositoryServiceImpl implements AddressRepositoryService {
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;

    public AddressRepositoryServiceImpl(CityRepository cityRepository,
                                        AddressRepository addressRepository) {
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public City fetchCityByIbge(Long cityIbge) {
        return cityRepository.findCityByIbge(cityIbge)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public City fetchCityByName(String name) {
        return cityRepository.findCityByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
    }
}
