package com.jpcchaves.adotar.application.utils.user;

import com.jpcchaves.adotar.application.dto.address.AddressResponseDto;
import com.jpcchaves.adotar.application.dto.contact.ContactDto;
import com.jpcchaves.adotar.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.application.service.address.contracts.AddressService;
import com.jpcchaves.adotar.application.utils.global.GlobalUtils;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.City;
import com.jpcchaves.adotar.domain.model.Contact;
import com.jpcchaves.adotar.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    private final MapperUtils mapperUtils;
    private final GlobalUtils globalUtils;
    private final AddressService addressService;

    public UserUtils(
            GlobalUtils globalUtils,
            MapperUtils mapperUtils,
            AddressService addressService) {
        this.globalUtils = globalUtils;
        this.mapperUtils = mapperUtils;
        this.addressService = addressService;
    }

    public UserDetailsDto buildUserDetails(User user) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();

        Address userAddress = user.getAddress();
        Contact userContact = user.getContact();

        buildUserAttributes(user, userDetailsDto);

        if (isAddressNotNull(userAddress)) {
            AddressResponseDto addressResponseDto = new AddressResponseDto();
            City city = addressService.fetchCityByName(userAddress.getCity());

            addressResponseDto.setCity(city.getIbge().toString());
            addressResponseDto.setCityName(city.getName());
            addressResponseDto.setZipcode(userAddress.getZipcode());
            addressResponseDto.setStateName(city.getState().getName());
            addressResponseDto.setState(city.getState().getId().toString());
            addressResponseDto.setNeighborhood(userAddress.getNeighborhood());
            addressResponseDto.setStreet(userAddress.getStreet());
            addressResponseDto.setNumber(userAddress.getNumber());
            addressResponseDto.setComplement(userAddress.getComplement());

            userDetailsDto.setAddress(addressResponseDto);
        }

        if (isContactNotNull(userContact)) {
            ContactDto contactDto = mapperUtils.parseObject(user.getContact(), ContactDto.class);
            userDetailsDto.setContact(contactDto);
        }

        return userDetailsDto;
    }

    private void buildUserAttributes(
            User user,
            UserDetailsDto userDetailsDto) {
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
