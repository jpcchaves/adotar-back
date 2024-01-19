package com.jpcchaves.adotar.service.address;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.repository.AddressRepository;
import com.jpcchaves.adotar.repository.CityRepository;
import com.jpcchaves.adotar.service.address.contracts.AddressRepositoryService;
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
