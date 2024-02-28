package com.jpcchaves.adotar.application.service.usecases.v1;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.application.dto.user.UserPictureDto;
import com.jpcchaves.adotar.application.dto.user.UserUpdateNameDTO;

public interface UserService {
    UserDetailsDto getUserDetails();

    ApiMessageResponseDto updateUserPicture(UserPictureDto userPictureDto);

    ApiMessageResponseDto updateFirstAndLastname(UserUpdateNameDTO requestDTO);
}
