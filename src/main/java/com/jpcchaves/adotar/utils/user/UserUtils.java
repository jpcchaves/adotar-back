package com.jpcchaves.adotar.utils.user;

import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    private final MapperUtils mapperUtils;

    public UserUtils(MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }

    public UserDetailsDto buildUserDetails(User user) {
        return new UserDetailsDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                mapperUtils.parseObject(user.getContact(), ContactDto.class),
                mapperUtils.parseObject(user.getAddress(), AddressDto.class),
                user.getLastSeen(),
                user.getUpdatedAt(),
                user.getCreatedAt()
        );
    }
}
