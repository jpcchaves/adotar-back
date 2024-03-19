package com.jpcchaves.adotar.application.service.auth;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.auth.*;
import com.jpcchaves.adotar.application.dto.user.UserDto;
import com.jpcchaves.adotar.application.service.JwtTokenProvider;
import com.jpcchaves.adotar.application.service.auth.contracts.AuthService;
import com.jpcchaves.adotar.application.service.auth.contracts.AuthUtils;
import com.jpcchaves.adotar.application.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.domain.Enum.UserRoles;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import com.jpcchaves.adotar.domain.exception.PasswordsMismatchException;
import com.jpcchaves.adotar.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.domain.model.User;
import com.jpcchaves.adotar.factory.jwt.JwtAuthResponseFactory;
import com.jpcchaves.adotar.infra.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  private static final String ROLE_USER = UserRoles.ROLE_USER.getRole();

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtAuthResponseFactory jwtAuthResponseFactory;
  private final SecurityContextService securityContextService;
  private final AuthUtils authUtils;
  private final MapperUtils mapperUtils;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthServiceImpl(
      AuthenticationManager authenticationManager,
      UserRepository userRepository,
      JwtAuthResponseFactory jwtAuthResponseFactory,
      SecurityContextService securityContextService,
      AuthUtils authUtils,
      MapperUtils mapperUtils,
      JwtTokenProvider jwtTokenProvider) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.jwtAuthResponseFactory = jwtAuthResponseFactory;
    this.securityContextService = securityContextService;
    this.authUtils = authUtils;
    this.mapperUtils = mapperUtils;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public JwtAuthResponseDto loginV2(LoginDtoV2 loginDtoV2) {
    try {
      User user = authUtils.fetchUserByEmail(loginDtoV2.getEmail());

      Authentication authentication =
          authenticationManager.authenticate(
              authUtils.buildNewAuthentication(
                  loginDtoV2.getEmail(), loginDtoV2.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenProvider.generateToken(authentication);

      UserDto userDto = authUtils.copyPropertiesFromUserToUserDto(user);

      authUtils.updateLastSeen(user);
      userRepository.save(user);

      return jwtAuthResponseFactory.createJwtAuthResponse(token, userDto);
    } catch (AuthenticationException ex) {
      throw new BadCredentialsException(
          "Usuário inexistente ou senha inválida");
    }
  }

  @Override
  public JwtAuthResponseDto login(LoginDto loginDto) {
    try {
      User user =
          authUtils.fetchUserByUsernameOrEmail(loginDto.getUsernameOrEmail());

      Authentication authentication =
          authenticationManager.authenticate(
              authUtils.buildNewAuthentication(
                  loginDto.getUsernameOrEmail(), loginDto.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtTokenProvider.generateToken(authentication);

      UserDto userDto = authUtils.copyPropertiesFromUserToUserDto(user);

      authUtils.updateLastSeen(user);
      userRepository.save(user);

      return jwtAuthResponseFactory.createJwtAuthResponse(token, userDto);
    } catch (AuthenticationException ex) {
      throw new BadCredentialsException(
          "Usuário inexistente ou senha inválida");
    }
  }

  @Override
  @Transactional
  public RegisterResponseDto register(RegisterRequestDto registerDto) {
    authUtils.checkEmailAvailability(registerDto.getEmail());
    authUtils.checkPasswordsMatch(
        registerDto.getPassword(), registerDto.getConfirmPassword());

    User user = authUtils.copyPropertiesFromRegisterDtoToUser(registerDto);
    authUtils.defineUserRole(user, ROLE_USER);

    user.setAdmin(false);
    user.setActive(true);

    User newUser = userRepository.save(user);

    return mapperUtils.parseObject(newUser, RegisterResponseDto.class);
  }

  @Override
  public JwtAuthResponseDto verifyToken(TokenDto tokenDto) {
    String token = tokenDto.getAccessToken();

    authUtils.isTokenValid(token);
    String userEmail = authUtils.getUserEmailFromToken(token);

    User user = authUtils.fetchUserByEmail(userEmail);

    UserDto userDto = authUtils.copyPropertiesFromUserToUserDto(user);

    return jwtAuthResponseFactory.createJwtAuthResponse(
        tokenDto.getAccessToken(), userDto);
  }

  @Override
  public ApiMessageResponseDto update(
      UpdateUserRequestDto updateUserDto, Long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Usuário não encontrado com o id: " + id));

    if (authUtils.passwordsMatches(
        updateUserDto.getCurrentPassword(), user.getPassword())) {

      authUtils.updateUser(user, updateUserDto);

      userRepository.save(user);

      return new ApiMessageResponseDto("Usuário atualizado com sucesso");
    } else {
      throw new BadRequestException(
          "A senha atual não condiz com a senha cadastrada anteriormente.");
    }
  }

  @Override
  public ApiMessageResponseDto updatePassword(
      UpdateUserPasswordRequestDTO requestDTO) {
    authUtils.checkPasswordsMatch(
        requestDTO.getNewPassword(), requestDTO.getConfirmNewPassword());

    User currentUser = securityContextService.getCurrentLoggedUser();
    User user =
        userRepository
            .findById(currentUser.getId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Ocorreu um erro inesperado. Por favor, tente novamente."));

    if (!authUtils.passwordsMatches(
        requestDTO.getCurrentPassword(), user.getPassword())) {
      throw new PasswordsMismatchException(
          "A senha atual não coincide com a senha cadastrada no sistema");
    }

    user.setPassword(authUtils.encodePassword(requestDTO.getNewPassword()));

    userRepository.save(user);

    return new ApiMessageResponseDto("Senha atualizada com sucesso!");
  }
}
