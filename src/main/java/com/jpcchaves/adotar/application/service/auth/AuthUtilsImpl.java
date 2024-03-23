package com.jpcchaves.adotar.application.service.auth;

import com.jpcchaves.adotar.application.dto.auth.RegisterRequestDto;
import com.jpcchaves.adotar.application.dto.auth.UpdateUserRequestDto;
import com.jpcchaves.adotar.application.dto.user.UserDto;
import com.jpcchaves.adotar.application.service.auth.contracts.AuthUtils;
import com.jpcchaves.adotar.application.service.jwt.contracts.JwtTokenProvider;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import com.jpcchaves.adotar.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.domain.model.Role;
import com.jpcchaves.adotar.domain.model.User;
import com.jpcchaves.adotar.infra.repository.RoleRepository;
import com.jpcchaves.adotar.infra.repository.UserRepository;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUtilsImpl implements AuthUtils {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  public AuthUtilsImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      JwtTokenProvider jwtTokenProvider,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
  }

  public void checkEmailAvailability(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new BadRequestException(
          "Já existe um usuário cadastrado com o email informado");
    }
  }

  public void checkPasswordsMatch(String password, String confirmPassword) {
    if (!password.equals(confirmPassword)) {
      throw new BadRequestException("As senhas não são iguais!");
    }
  }

  public void isTokenValid(String token) {
    jwtTokenProvider.validateToken(token);
  }

  public String getUserEmailFromToken(String token) {
    final String CLAIM_EMAIL_KEY = "email";
    return jwtTokenProvider.getClaimFromTokenByKey(token, CLAIM_EMAIL_KEY);
  }

  public void updateLastSeen(User user) {
    user.setLastSeen(new Date());
  }

  public Authentication buildNewAuthentication(
      String usernameOrEmail, String password) {
    return new UsernamePasswordAuthenticationToken(usernameOrEmail, password);
  }

  public User fetchUserByUsernameOrEmail(String usernameOrEmail) {
    return userRepository
        .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Usuário não encontrado com os dados informados: "
                        + usernameOrEmail));
  }

  public User fetchUserByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Usuário não encontrado com o email informado: " + email));
  }

  public void defineUserRole(User user, String role) {
    Set<Role> roles = new HashSet<>();
    Optional<Role> userRole = roleRepository.findByName(role);

    if (userRole.isPresent()) {
      roles.add(userRole.get());
      user.setRoles(roles);
    }
  }

  public void updateUser(User user, UpdateUserRequestDto updateUserDto) {
    user.setFirstName(updateUserDto.getFirstName());
    user.setLastName(updateUserDto.getLastName());
    user.setPassword(encodePassword(updateUserDto.getPassword()));
    user.setAdmin(false);
    user.setRoles(user.getRoles());
  }

  public UserDto copyPropertiesFromUserToUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setEmail(user.getEmail());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setUsername(user.getUsername());
    userDto.setAdmin(user.getAdmin());
    userDto.setActive(user.getActive());
    userDto.setCreatedAt(user.getCreatedAt());
    userDto.setUpdatedAt(user.getUpdatedAt());
    userDto.setDeletedAt(user.getDeletedAt());
    userDto.setPhotoUrl(user.getPhotoUrl());
    userDto.setName(user.getFirstName() + " " + user.getLastName());

    for (Role role : user.getRoles()) {
      userDto.getRoles().add(role.getName());
    }

    return userDto;
  }

  public User copyPropertiesFromRegisterDtoToUser(
      RegisterRequestDto registerDto) {
    User user = new User();
    user.setFirstName(registerDto.getFirstName());
    user.setLastName(registerDto.getLastName());
    user.setEmail(registerDto.getEmail());
    user.setUsername(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    return user;
  }

  public Boolean passwordsMatches(String currentPassword, String password) {
    return passwordEncoder.matches(currentPassword, password);
  }

  public String encodePassword(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
}
