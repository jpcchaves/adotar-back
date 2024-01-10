package com.jpcchaves.adotar.service.address;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.service.address.contracts.AddressRepositoryService;
import com.jpcchaves.adotar.service.address.contracts.AddressService;
import com.jpcchaves.adotar.service.address.contracts.AddressUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepositoryService addressRepositoryService;
    private final AddressUtils addressUtils;

    public AddressServiceImpl(AddressRepositoryService addressRepositoryService,
                              AddressUtils addressUtils) {
        this.addressRepositoryService = addressRepositoryService;
        this.addressUtils = addressUtils;
    }

    @Override
    public City fetchCityByIbge(Long cityIbge) {
        return addressRepositoryService.fetchCityByIbge(cityIbge);
    }

    @Override
    public Address createAddress(AddressRequestDto addressDto,
                                 City city) {
        return addressUtils.createAddress(addressDto, city);
    }
}
