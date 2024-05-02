package com.jpcchaves.adotar.infrastructure.application.service.address;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressDto;
import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressRequestDto;
import com.jpcchaves.adotar.infrastructure.application.service.address.contracts.AddressRepositoryService;
import com.jpcchaves.adotar.infrastructure.application.service.address.contracts.AddressService;
import com.jpcchaves.adotar.infrastructure.application.service.address.contracts.AddressUtils;
import com.jpcchaves.adotar.infrastructure.application.service.auth.contracts.SecurityContextService;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.exception.BadRequestException;
import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.model.Address;
import com.jpcchaves.adotar.infrastructure.domain.model.City;
import com.jpcchaves.adotar.infrastructure.domain.model.User;
import com.jpcchaves.adotar.infrastructure.infra.repository.UserRepository;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
  private final SecurityContextService securityContextService;
  private final UserRepository userRepository;
  private final AddressRepositoryService addressRepositoryService;
  private final AddressUtils addressUtils;
  private final MapperUtils mapper;

  public AddressServiceImpl(
      SecurityContextService securityContextService,
      UserRepository userRepository,
      AddressRepositoryService addressRepositoryService,
      AddressUtils addressUtils,
      MapperUtils mapper) {
    this.securityContextService = securityContextService;
    this.userRepository = userRepository;
    this.addressRepositoryService = addressRepositoryService;
    this.addressUtils = addressUtils;
    this.mapper = mapper;
  }

  @Override
  public City fetchCityByIbge(Long cityIbge) {
    return addressRepositoryService.fetchCityByIbge(cityIbge);
  }

  @Override
  public Address buildAddress(AddressRequestDto addressDto, City city) {
    return addressUtils.buildAddress(addressDto, city);
  }

  @Override
  public AddressDto getUserAddress() {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found!"));
    Address address = currentUser.getAddress();

    if (Objects.isNull(address)) {
      throw new BadRequestException(
          "O usuario ainda nao possui um endereco cadastrado!");
    }

    return mapper.parseObject(address, AddressDto.class);
  }

  @Override
  public AddressDto updateUserAddress(AddressRequestDto addressDto) {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found!"));
    Address address = currentUser.getAddress();

    City city =
        addressRepositoryService.fetchCityByIbge(addressDto.getCityIbge());

    addressUtils.updateAddress(address, city, addressDto);

    Address updatedAddress = addressRepositoryService.saveAddress(address);

    return mapper.parseObject(updatedAddress, AddressDto.class);
  }

  @Override
  public ApiMessageResponseDto createAddress(AddressRequestDto addressDto) {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found!"));

    if (!Objects.isNull(currentUser.getAddress())) {
      throw new BadRequestException(
          "O usuario ja possui um endereco cadastrado!");
    }

    City city =
        addressRepositoryService.fetchCityByIbge(addressDto.getCityIbge());

    Address address =
        new Address(
            addressDto.getZipcode(),
            addressDto.getStreet(),
            addressDto.getNumber(),
            addressDto.getComplement(),
            addressDto.getNeighborhood(),
            city.getName(),
            city.getState().getName());

    currentUser.setAddress(address);

    userRepository.save(currentUser);

    return new ApiMessageResponseDto("Endereco cadastrado com sucesso!");
  }

  @Override
  public City fetchCityByName(String name) {
    return addressRepositoryService.fetchCityByName(name);
  }
}
