package com.jpcchaves.adotar.infrastructure.application.service.user;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserPictureDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserUpdateNameDTO;
import com.jpcchaves.adotar.infrastructure.application.service.auth.contracts.SecurityContextService;
import com.jpcchaves.adotar.infrastructure.application.service.user.contracts.UserService;
import com.jpcchaves.adotar.infrastructure.application.utils.user.UserUtils;
import com.jpcchaves.adotar.infrastructure.domain.exception.UnexpectedErrorException;
import com.jpcchaves.adotar.infrastructure.domain.model.User;
import com.jpcchaves.adotar.infrastructure.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final SecurityContextService securityContextService;
  private final UserUtils userUtils;
  private final UserRepository userRepository;

  public UserServiceImpl(
      SecurityContextService securityContextService,
      UserUtils userUtils,
      UserRepository userRepository) {
    this.securityContextService = securityContextService;
    this.userUtils = userUtils;
    this.userRepository = userRepository;
  }

  @Override
  public UserDetailsDto getUserDetails() {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () ->
                    new UnexpectedErrorException(
                        "Ocorreu um erro inesperado, tente novamente"));

    return userUtils.buildUserDetails(currentUser);
  }

  @Override
  public ApiMessageResponseDto updateUserPicture(
      UserPictureDto userPictureDto) {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () ->
                    new UnexpectedErrorException(
                        "Ocorreu um erro inesperado, tente novamente"));

    currentUser.setPhotoUrl(userPictureDto.getPictureUrl());

    userRepository.save(currentUser);

    return new ApiMessageResponseDto("Foto adicionada com sucesso!");
  }

  @Override
  public ApiMessageResponseDto updateFirstAndLastname(
      UserUpdateNameDTO requestDTO) {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () ->
                    new UnexpectedErrorException(
                        "Ocorreu um erro inesperado, tente novamente"));

    currentUser.setFirstName(requestDTO.getFirstName());
    currentUser.setLastName(requestDTO.getLastName());

    userRepository.save(currentUser);

    return new ApiMessageResponseDto("Dados atualizados com sucesso!");
  }
}
