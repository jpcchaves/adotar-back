package com.jpcchaves.adotar.infrastructure.application.service.user.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserPictureDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserUpdateNameDTO;

public interface UserService {
  UserDetailsDto getUserDetails();

  ApiMessageResponseDto updateUserPicture(UserPictureDto userPictureDto);

  ApiMessageResponseDto updateFirstAndLastname(UserUpdateNameDTO requestDTO);
}
