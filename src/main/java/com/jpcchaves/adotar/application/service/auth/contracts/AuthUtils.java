package com.jpcchaves.adotar.application.service.auth.contracts;

import com.jpcchaves.adotar.application.dto.auth.RegisterRequestDto;
import com.jpcchaves.adotar.application.dto.auth.UpdateUserRequestDto;
import com.jpcchaves.adotar.application.dto.user.UserDto;
import com.jpcchaves.adotar.domain.model.User;
import org.springframework.security.core.Authentication;

public interface AuthUtils {
    void checkEmailAvailability(String email);

    void checkPasswordsMatch(String password,
                             String confirmPassword);

    void isTokenValid(String token);

    String getUserEmailFromToken(String token);

    void updateLastSeen(User user);

    Authentication buildNewAuthentication(String usernameOrEmail,
                                          String password);

    User fetchUserByUsernameOrEmail(String usernameOrEmail);

    User fetchUserByEmail(String email);

    void defineUserRole(User user,
                        String role);

    void updateUser(User user,
                    UpdateUserRequestDto updateUserDto);

    UserDto copyPropertiesFromUserToUserDto(User user);

    User copyPropertiesFromRegisterDtoToUser(RegisterRequestDto registerDto);

    Boolean passwordsMatches(String currentPassword,
                             String password);

    String encodePassword(String rawPassword);
}
