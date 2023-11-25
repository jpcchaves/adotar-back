package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.payload.dto.user.UserPictureDto;

public interface UserService {
    UserDetailsDto getUserDetails();

    ApiMessageResponseDto updateUserPicture(UserPictureDto userPictureDto);
}
