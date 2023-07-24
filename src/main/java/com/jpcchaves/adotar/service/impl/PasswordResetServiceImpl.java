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
        User user = userRepository
                .findByEmail(passwordResetRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o email informado. Por favor, verifique o email e tente novamente"));

        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByUser(user);

        if (passwordResetToken.isPresent()) {

            PasswordResetToken token = passwordResetToken.get();

            if (isTokenExpired(token.getExpirationTime())) {

                deleteExpiredToken(token);
                PasswordResetToken newToken = buildNewToken(user);
                emailService.sendPasswordRequest(newToken);

            } else {
                emailService.sendPasswordRequest(passwordResetToken.get());
            }
        } else {
            emailService.sendPasswordRequest(buildNewToken(user));
        }

        return new ApiMessageResponseDto("Solicitação enviada com sucesso!");
    }

    @Override
    public ApiMessageResponseDto resetPassword(PasswordResetTokenRequestDto passwordResetTokenRequestDto) {

        if (!passwordsMatch(passwordResetTokenRequestDto.getNewPassword(), passwordResetTokenRequestDto.getConfirmNewPassword())) {
            throw new PasswordsMismatchException("As senhas não são indênticas. Verifique os dados informados e tente novamente");
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .findByToken(passwordResetTokenRequestDto.getToken())
                .orElseThrow(() -> new ResourceNotFoundException("O token informado está expirado ou não existe. Por favor, verifique os dados e tente novamente."));

        if (isTokenExpired(passwordResetToken.getExpirationTime())) {
            deleteExpiredToken(passwordResetToken);
            throw new TokenExpiredException("O token informado não existe ou está expirado. Verifique os dados informados ou solicite um novo token para continuar");
        }

        User user = userRepository
                .findByEmail(passwordResetToken.getUser().getEmail())
                .orElseThrow(() -> new UserNotFoundException("Ocorreu um erro inesperado. Por favor, tente novamente"));

        user.setPassword(passwordEncoder.encode(passwordResetTokenRequestDto.getNewPassword()));

        userRepository.save(user);

        deleteExpiredToken(passwordResetToken);

        return new ApiMessageResponseDto("Senha redefinida com sucesso!");
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

    private Boolean passwordsMatch(String newPassword,
                                   String confirmNewPassword) {
        return newPassword.equals(confirmNewPassword);
    }

    private Boolean isTokenExpired(Instant expirationTime) {
        return Instant.now().isAfter(expirationTime);
    }

    private PasswordResetToken buildNewToken(User user) {
        String token = generateRandomCode();
        Instant expirationDate = calculateExpirationDate();
        return passwordResetTokenRepository.save(new PasswordResetToken(token, expirationDate, user));
    }
}
