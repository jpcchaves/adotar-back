package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.repository.AddressRepository;
import com.jpcchaves.adotar.service.usecases.AddressService;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final SecurityContextService securityContextService;
    private final MapperUtils mapperUtils;

    public AddressServiceImpl(AddressRepository addressRepository,
                              SecurityContextService securityContextService,
                              MapperUtils mapperUtils) {
        this.addressRepository = addressRepository;
        this.securityContextService = securityContextService;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public AddressDto getUserAddress() {
        Address address = securityContextService.getCurrentLoggedUser().getAddress();
        return mapperUtils.parseObject(address, AddressDto.class);
    }

    @Override
    public AddressDto updateUserAddress(AddressDto addressDto) {
        Address address = securityContextService.getCurrentLoggedUser().getAddress();

        address.setZipcode(addressDto.getZipcode());
        address.setState(addressDto.getState());
        address.setCity(addressDto.getCity());
        address.setNeighborhood(addressDto.getNeighborhood());
        address.setComplement(addressDto.getComplement());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());

        Address updatedAddress = addressRepository.save(address);

        return mapperUtils.parseObject(updatedAddress, AddressDto.class);
    }
}
