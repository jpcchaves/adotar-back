package com.jpcchaves.adotar.service.address;

import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.repository.CityRepository;
import com.jpcchaves.adotar.service.address.contracts.AddressRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class AddressRepositoryServiceImpl implements AddressRepositoryService {
    private final CityRepository cityRepository;

    public AddressRepositoryServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City fetchCityByIbge(Long cityIbge) {
        return cityRepository.findCityByIbge(cityIbge)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada!"));
    }
}
