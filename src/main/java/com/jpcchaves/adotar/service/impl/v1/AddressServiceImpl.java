package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.repository.AddressRepository;
import com.jpcchaves.adotar.repository.CityRepository;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.service.usecases.v1.AddressService;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;
    private final MapperUtils mapperUtils;

    public AddressServiceImpl(AddressRepository addressRepository,
                              CityRepository cityRepository,
                              UserRepository userRepository,
                              SecurityContextService securityContextService,
                              MapperUtils mapperUtils) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
        this.securityContextService = securityContextService;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public AddressDto getUserAddress() {
        User user = securityContextService.getCurrentLoggedUser();
        User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        Address address = currentUser.getAddress();

        if (Objects.isNull(address)) {
            throw new BadRequestException("O usuario ainda nao possui um endereco cadastrado!");
        }

        return mapperUtils.parseObject(address, AddressDto.class);
    }

    @Override
    public ApiMessageResponseDto createAddress(AddressRequestDto addressDto) {
        User user = securityContextService.getCurrentLoggedUser();
        User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if (!Objects.isNull(currentUser.getAddress())) {
            throw new BadRequestException("O usuario ja possui um endereco cadastrado!");
        }

        City city = cityRepository.findById(addressDto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada"));

        Address address = new Address(
                addressDto.getZipcode(),
                addressDto.getStreet(),
                addressDto.getNumber(),
                addressDto.getComplement(),
                addressDto.getNeighborhood(),
                city.getName(),
                city.getState().getName()
        );

        currentUser.setAddress(address);

        userRepository.save(currentUser);

        return new ApiMessageResponseDto("Endereco cadastrado com sucesso!");
    }

    @Override
    public AddressDto updateUserAddress(AddressRequestDto addressDto) {
        Address address = securityContextService.getCurrentLoggedUser().getAddress();

        City city = cityRepository.findById(addressDto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada"));

        updateAddress(address, city, addressDto);

        Address updatedAddress = addressRepository.save(address);

        return mapperUtils.parseObject(updatedAddress, AddressDto.class);
    }

    private void updateAddress(Address address,
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
