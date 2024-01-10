package com.jpcchaves.adotar.service.address;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.service.address.contracts.AddressUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressUtilsImpl implements AddressUtils {

    @Override
    public Address createAddress(AddressRequestDto addressDto,
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
}
