package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.Enum.ExpirationTime;
import com.jpcchaves.adotar.domain.entities.PasswordResetToken;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.PasswordsMismatchException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.exception.TokenExpiredException;
import com.jpcchaves.adotar.exception.UserNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetTokenRequestDto;
import com.jpcchaves.adotar.repository.PasswordResetTokenRepository;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.service.usecases.EmailService;
import com.jpcchaves.adotar.service.usecases.PasswordResetService;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    private static final int TOKEN_VALIDITY_MINUTES = ExpirationTime.FIVE_MINUTES.getMinutes();

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository,
                                    UserRepository userRepository,
                                    EmailService emailService,
                                    PasswordEncoder passwordEncoder) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ApiMessageResponseDto resetTokenRequestDto(PasswordResetRequestDto passwordResetRequestDto) throws MessagingException {
        User user = getUserByEmail(passwordResetRequestDto.getEmail());

        PasswordResetToken token = generatePasswordResetToken(user);
        sendPasswordRequest(token);

        return new ApiMessageResponseDto("Solicitação enviada com sucesso!");
    }

    @Override
    public ApiMessageResponseDto resetPassword(PasswordResetTokenRequestDto passwordResetTokenRequestDto) {
        String newPassword = passwordResetTokenRequestDto.getNewPassword();
        String confirmNewPassword = passwordResetTokenRequestDto.getConfirmNewPassword();

        if (!passwordsMatch(newPassword, confirmNewPassword)) {
            throw new PasswordsMismatchException("As senhas não são indênticas. Verifique os dados informados e tente novamente");
        }

        PasswordResetToken passwordResetToken = getPasswordResetToken(passwordResetTokenRequestDto.getToken());

        if (isTokenExpired(passwordResetToken.getExpirationTime())) {
            deleteExpiredToken(passwordResetToken);
            throw new TokenExpiredException("O token informado não existe ou está expirado. Verifique os dados informados ou solicite um novo token para continuar");
        }

        User user = getUserByEmail(passwordResetToken.getUser().getEmail());
        user.setPassword(encodePassword(passwordResetTokenRequestDto.getNewPassword()));
        userRepository.save(user);

        deleteExpiredToken(passwordResetToken);

        return new ApiMessageResponseDto("Senha redefinida com sucesso!");
    }

    private PasswordResetToken generatePasswordResetToken(User user) {
        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByUser(user);

        if (passwordResetToken.isPresent()) {
            PasswordResetToken token = passwordResetToken.get();

            if (isTokenExpired(token.getExpirationTime())) {
                deleteExpiredToken(token);
            } else {
                return token;
            }
        }

        return buildNewToken(user);
    }

    private PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("O token informado está expirado ou não existe. Por favor, verifique os dados e tente novamente."));
    }

    private User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Ocorreu um erro inesperado. Por favor, tente novamente"));
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private void sendPasswordRequest(PasswordResetToken token) throws MessagingException {
        emailService.sendPasswordRequest(token);
    }

    private Instant calculateExpirationDate() {
        return Instant.now().plus(TOKEN_VALIDITY_MINUTES, ChronoUnit.MINUTES);
    }

    private void deleteExpiredToken(PasswordResetToken token) {
        passwordResetTokenRepository.delete(token);
    }

    private String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    private PasswordResetToken buildNewToken(User user) {
        String token = generateRandomCode();
        Instant expirationDate = calculateExpirationDate();
        return passwordResetTokenRepository.save(new PasswordResetToken(token, expirationDate, user));
    }

    private Boolean passwordsMatch(String newPassword,
                                   String confirmNewPassword) {
        return newPassword.equals(confirmNewPassword);
    }

    private Boolean isTokenExpired(Instant expirationTime) {
        return Instant.now().isAfter(expirationTime);
    }

}
