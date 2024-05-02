package com.jpcchaves.adotar.infrastructure.application.utils.user;

import com.jpcchaves.adotar.infrastructure.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.contact.ContactDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.infrastructure.application.service.address.contracts.AddressService;
import com.jpcchaves.adotar.infrastructure.application.utils.global.GlobalUtils;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.model.Address;
import com.jpcchaves.adotar.infrastructure.domain.model.City;
import com.jpcchaves.adotar.infrastructure.domain.model.Contact;
import com.jpcchaves.adotar.infrastructure.domain.model.User;
import com.jpcchaves.adotar.infrastructure.factory.address.AddressFactory;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
  private final MapperUtils mapperUtils;
  private final GlobalUtils globalUtils;
  private final AddressService addressService;
  private final AddressFactory addressFactory;

  public UserUtils(
      GlobalUtils globalUtils,
      MapperUtils mapperUtils,
      AddressService addressService,
      AddressFactory addressFactory) {
    this.globalUtils = globalUtils;
    this.mapperUtils = mapperUtils;
    this.addressService = addressService;
    this.addressFactory = addressFactory;
  }

  public UserDetailsDto buildUserDetails(User user) {
    UserDetailsDto userDetailsDto = new UserDetailsDto();

    Address userAddress = user.getAddress();
    Contact userContact = user.getContact();

    buildUserAttributes(user, userDetailsDto);

    if (isAddressNotNull(userAddress)) {
      City city = addressService.fetchCityByName(userAddress.getCity());

      AddressResponseDto addressResponseDto =
          addressFactory.createAddressResponseDto(
              userAddress.getZipcode(),
              userAddress.getStreet(),
              userAddress.getNumber(),
              userAddress.getComplement(),
              userAddress.getNeighborhood(),
              city.getIbge().toString(),
              city.getName(),
              city.getState().getId().toString(),
              city.getState().getName());

      userDetailsDto.setAddress(addressResponseDto);
    }

    if (isContactNotNull(userContact)) {
      ContactDto contactDto =
          mapperUtils.parseObject(user.getContact(), ContactDto.class);
      userDetailsDto.setContact(contactDto);
    }

    return userDetailsDto;
  }

  private void buildUserAttributes(User user, UserDetailsDto userDetailsDto) {
    userDetailsDto.setId(user.getId());
    userDetailsDto.setFirstName(user.getFirstName());
    userDetailsDto.setLastName(user.getLastName());
    userDetailsDto.setUsername(user.getUsername());
    userDetailsDto.setName(buildUserFullName(user));
    userDetailsDto.setEmail(user.getEmail());
    userDetailsDto.setUpdatedAt(user.getUpdatedAt());
    userDetailsDto.setLastSeen(user.getLastSeen());
    userDetailsDto.setCreatedAt(user.getCreatedAt());
    userDetailsDto.setContact(null);
    userDetailsDto.setAddress(null);
  }

  private String buildUserFullName(User user) {
    return user.getFirstName() + " " + user.getLastName();
  }

  private boolean isAddressNotNull(Address address) {
    return globalUtils.isObjectNotNull(address);
  }

  private boolean isContactNotNull(Contact contact) {
    return globalUtils.isObjectNotNull(contact);
  }
}
