package com.jpcchaves.adotar.application.service.pwdreset;

import com.jpcchaves.adotar.application.service.pwdreset.contracts.PwdResetUtils;
import com.jpcchaves.adotar.application.service.usecases.EmailService;
import com.jpcchaves.adotar.domain.Enum.ExpirationTime;
import com.jpcchaves.adotar.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.domain.exception.UserNotFoundException;
import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.domain.model.User;
import com.jpcchaves.adotar.infra.repository.PasswordResetTokenRepository;
import com.jpcchaves.adotar.infra.repository.UserRepository;
import jakarta.mail.MessagingException;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PwdResetUtilsImpl implements PwdResetUtils {
    private static final int TOKEN_VALIDITY_MINUTES =
            ExpirationTime.FIVE_MINUTES.getMinutes();

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public PwdResetUtilsImpl(
            PasswordResetTokenRepository passwordResetTokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            EmailService emailService) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public PasswordResetToken generatePasswordResetToken(User user) {
        Optional<PasswordResetToken> passwordResetToken =
                passwordResetTokenRepository.findByUser(user);

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

    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository
                .findByToken(token)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "O token informado está expirado ou não existe. Por favor, verifique os dados e tente novamente."));
    }

    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () ->
                                new UserNotFoundException(
                                        "Ocorreu um erro inesperado. Por favor, tente novamente"));
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void sendPasswordRequest(PasswordResetToken token)
            throws MessagingException {
        emailService.sendResetPasswordRequest(token);
    }

    public Instant calculateExpirationDate() {
        return Instant.now().plus(TOKEN_VALIDITY_MINUTES, ChronoUnit.MINUTES);
    }

    public void deleteExpiredToken(PasswordResetToken token) {
        passwordResetTokenRepository.delete(token);
    }

    public String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    public PasswordResetToken buildNewToken(User user) {
        String token = generateRandomCode();
        Instant expirationDate = calculateExpirationDate();
        return passwordResetTokenRepository.save(
                new PasswordResetToken(token, expirationDate, user));
    }

    public Boolean passwordsMatch(String newPassword,
                                  String confirmNewPassword) {
        return newPassword.equals(confirmNewPassword);
    }

    public Boolean isTokenExpired(Instant expirationTime) {
        return Instant.now().isAfter(expirationTime);
    }
}
