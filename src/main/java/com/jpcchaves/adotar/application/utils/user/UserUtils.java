package com.jpcchaves.adotar.application.utils.user;

import com.jpcchaves.adotar.application.utils.global.GlobalUtils;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.domain.model.Address;
import com.jpcchaves.adotar.domain.model.Contact;
import com.jpcchaves.adotar.domain.model.User;
import com.jpcchaves.adotar.application.dto.address.AddressDto;
import com.jpcchaves.adotar.application.dto.contact.ContactDto;
import com.jpcchaves.adotar.application.dto.user.UserDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    private final MapperUtils mapperUtils;
    private final GlobalUtils globalUtils;

    public UserUtils(
            GlobalUtils globalUtils,
            MapperUtils mapperUtils) {
        this.globalUtils = globalUtils;
        this.mapperUtils = mapperUtils;
    }

    public UserDetailsDto buildUserDetails(User user) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();

        Address userAddress = user.getAddress();
        Contact userContact = user.getContact();

        buildUserAttributes(user, userDetailsDto);

        if (isAddressNotNull(userAddress)) {
            AddressDto addressDto = mapperUtils.parseObject(userAddress, AddressDto.class);
            userDetailsDto.setAddress(addressDto);
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
