package com.jpcchaves.adotar.service.address;

import org.springframework.stereotype.Service;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.service.address.contracts.AddressUtils;

@Service
public class AddressUtilsImpl implements AddressUtils {

    @Override
    public Address buildAddress(AddressRequestDto addressDto,
                                City city) {
        return new Address(
                addressDto.getZipcode(),
                addressDto.getStreet(),
                addressDto.getNumber(),
                addressDto.getComplement(),
                addressDto.getNeighborhood(),
                city.getName(),
                city.getState().getName()
        );
    }

    @Override
    public void updateAddress(Address address,
                              City city,
                              AddressRequestDto addressDto) {
        address.setCity(city.getName());
        address.setState(city.getState().getName());
        address.setZipcode(addressDto.getZipcode());
        address.setNeighborhood(addressDto.getNeighborhood());
        address.setComplement(addressDto.getComplement());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
    }
}
