package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.Enum.UserRoles;
import com.jpcchaves.adotar.domain.entities.Address;
import com.jpcchaves.adotar.domain.entities.Contact;
import com.jpcchaves.adotar.domain.entities.Role;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.factory.address.AddressFactory;
import com.jpcchaves.adotar.factory.contact.ContactFactory;
import com.jpcchaves.adotar.factory.jwt.JwtAuthResponseFactory;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.*;
import com.jpcchaves.adotar.payload.dto.role.RoleDto;
import com.jpcchaves.adotar.payload.dto.user.UserDto;
import com.jpcchaves.adotar.repository.AddressRepository;
import com.jpcchaves.adotar.repository.ContactRepository;
import com.jpcchaves.adotar.repository.RoleRepository;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.security.JwtTokenProvider;
import com.jpcchaves.adotar.service.usecases.v1.AuthService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String ROLE_USER = UserRoles.ROLE_USER.getRole();

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final AddressFactory addressFactory;
    private final ContactFactory contactFactory;
    private final JwtAuthResponseFactory jwtAuthResponseFactory;
    private final PasswordEncoder passwordEncoder;
    private final MapperUtils mapperUtils;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            AddressRepository addressRepository,
            ContactRepository contactRepository,
            AddressFactory addressFactory,
            ContactFactory contactFactory,
            JwtAuthResponseFactory jwtAuthResponseFactory,
            PasswordEncoder passwordEncoder,
            MapperUtils mapperUtils,
            JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.addressFactory = addressFactory;
        this.contactFactory = contactFactory;
        this.jwtAuthResponseFactory = jwtAuthResponseFactory;
        this.passwordEncoder = passwordEncoder;
        this.mapperUtils = mapperUtils;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public JwtAuthResponseDto loginV2(LoginDtoV2 loginDtoV2) {
        try {
            User user = fetchUserByEmail(loginDtoV2.getEmail());

            Authentication authentication = authenticationManager
                    .authenticate(buildNewAuthentication(loginDtoV2.getEmail(), loginDtoV2.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            UserDto userDto = copyPropertiesFromUserToUserDto(user);

            updateLastSeen(user);
            userRepository.save(user);

            return jwtAuthResponseFactory.createJwtAuthResponse(token, userDto);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Usuário inexistente ou senha inválida");
        }
    }

    @Override
    public JwtAuthResponseDto login(LoginDto loginDto) {
        try {
            User user = fetchUserByUsernameOrEmail(loginDto.getUsernameOrEmail());

            Authentication authentication = authenticationManager
                    .authenticate(buildNewAuthentication(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            UserDto userDto = copyPropertiesFromUserToUserDto(user);

            updateLastSeen(user);
            userRepository.save(user);

            return jwtAuthResponseFactory.createJwtAuthResponse(token, userDto);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Usuário inexistente ou senha inválida");
        }
    }

    @Override
    @Transactional
    public RegisterResponseDto register(RegisterRequestDto registerDto) {
        checkEmailAvailability(registerDto.getEmail());
        checkPasswordsMatch(registerDto.getPassword(), registerDto.getConfirmPassword());

        User user = copyPropertiesFromRegisterDtoToUser(registerDto);
        defineUserRole(user, ROLE_USER);

        user.setAdmin(false);
        user.setActive(true);

        User newUser = userRepository.save(user);

        return mapperUtils.parseObject(newUser, RegisterResponseDto.class);
    }

    @Override
    public JwtAuthResponseDto verifyToken(TokenDto tokenDto) {
        String token = tokenDto.getAccessToken();

        isTokenValid(token);
        String userEmail = getUserEmailFromToken(token);

        User user = fetchUserByEmail(userEmail);

        UserDto userDto = copyPropertiesFromUserToUserDto(user);

        return jwtAuthResponseFactory.createJwtAuthResponse(tokenDto.getAccessToken(), userDto);
    }

    private void isTokenValid(String token) {
        jwtTokenProvider.validateToken(token);
    }

    private String getUserEmailFromToken(String token) {
        final String CLAIM_EMAIL_KEY = "email";
        return jwtTokenProvider.getClaimFromTokenByKey(token, CLAIM_EMAIL_KEY);
    }

    private void updateLastSeen(User user) {
        user.setLastSeen(new Date());
    }

    private Authentication buildNewAuthentication(
            String usernameOrEmail,
            String password) {
        return new UsernamePasswordAuthenticationToken(usernameOrEmail, password);
    }

    private User fetchUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado com os dados informados: " + usernameOrEmail));
    }

    private User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado com o email informado: " + email));
    }

    private void defineUserRole(
            User user,
            String role) {
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(role);

        if (userRole.isPresent()) {
            roles.add(userRole.get());
            user.setRoles(roles);
        }
    }

    private Contact createNewContact() {
        return contactRepository.save(contactFactory.createUserEmptyContact());
    }

    private Address createNewAddress() {
        return addressRepository.save(addressFactory.createUserEmptyAddress());
    }

    @Override
    public ApiMessageResponseDto update(
            UpdateUserRequestDto updateUserDto,
            Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));

        if (passwordsMatches(updateUserDto.getCurrentPassword(), user.getPassword())) {

            updateUser(user, updateUserDto);

            userRepository.save(user);

            return new ApiMessageResponseDto("Usuário atualizado com sucesso");
        } else {
            throw new BadRequestException("A senha atual não condiz com a senha cadastrada anteriormente.");
        }
    }

    private void checkEmailAvailability(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("Já existe um usuário cadastrado com o email informado");
        }
    }

    private void checkPasswordsMatch(
            String password,
            String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new BadRequestException("As senhas não são iguais!");
        }
    }

    private void updateUser(
            User user,
            UpdateUserRequestDto updateUserDto) {
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        user.setAdmin(false);
        user.setRoles(user.getRoles());
    }

    private UserDto copyPropertiesFromUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setRoles(mapperUtils.parseSetObjects(user.getRoles(), RoleDto.class));
        userDto.setAdmin(user.getAdmin());
        userDto.setActive(user.getActive());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setDeletedAt(user.getDeletedAt());
        userDto.setName(user.getFirstName() + " " + user.getLastName());

        return userDto;
    }

    private User copyPropertiesFromRegisterDtoToUser(RegisterRequestDto registerDto) {
        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return user;
    }

    private Boolean passwordsMatches(
            String currentPassword,
            String password) {
        return passwordEncoder.matches(currentPassword, password);
    }
}
