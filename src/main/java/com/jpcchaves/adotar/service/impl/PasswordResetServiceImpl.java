package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.PasswordResetToken;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.BadRequestException;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    private static final int TOKEN_VALIDITY_MINUTES = 5;

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
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado com o email informado. Por favor, verifique o email e tente novamente"));

        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByUser(user);

        if (passwordResetToken.isPresent()) {

            if (isTokenExpired(passwordResetToken.get().getExpirationTime())) {
                passwordResetTokenRepository.delete(passwordResetToken.get());

                String token = generateRandomCode();
                Instant expirationDate = calculateExpirationDate();

                PasswordResetToken newPasswordResetToken = new PasswordResetToken(token, expirationDate, user);

                PasswordResetToken savedPasswordResetToken = passwordResetTokenRepository.save(newPasswordResetToken);

                emailService.sendPasswordRequest(savedPasswordResetToken);
            } else {
                emailService.sendPasswordRequest(passwordResetToken.get());
            }
            
        } else {
            String token = generateRandomCode();
            Instant expirationDate = calculateExpirationDate();

            PasswordResetToken newPasswordResetToken = new PasswordResetToken(token, expirationDate, user);

            PasswordResetToken savedPasswordResetToken = passwordResetTokenRepository.save(newPasswordResetToken);

            emailService.sendPasswordRequest(savedPasswordResetToken);
        }

        return new ApiMessageResponseDto("Solicitação enviada com sucesso!");
    }

    @Override
    public ApiMessageResponseDto resetPassword(PasswordResetTokenRequestDto passwordResetTokenRequestDto) {

        if (!passwordsMatches(passwordResetTokenRequestDto.getNewPassword(), passwordResetTokenRequestDto.getConfirmNewPassword())) {
            throw new BadRequestException("As senhas não são indenticas. Verifique os dados informados e tente novamente");
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .findByToken(passwordResetTokenRequestDto.getToken())
                .orElseThrow(() -> new BadRequestException("Token inválido. Por favor, verifique o token informado e tente novamente"));

        if (isTokenExpired(passwordResetToken.getExpirationTime())) {
            passwordResetTokenRepository.delete(passwordResetToken);
            throw new BadRequestException("O token informado não existe ou está expirado. Verifique os dados informados ou solicite um novo token para continuar");
        }

        User user = userRepository
                .findByEmail(passwordResetToken.getUser().getEmail())
                .orElseThrow(() -> new BadRequestException("Ocorreu um erro inesperado. Por favor, tente novamente"));


        user.setPassword(passwordEncoder.encode(passwordResetTokenRequestDto.getNewPassword()));

        userRepository.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);

        return new ApiMessageResponseDto("Senha redefinida com sucesso!");
    }

    private Instant calculateExpirationDate() {
        return Instant.now().plus(TOKEN_VALIDITY_MINUTES, ChronoUnit.MINUTES);
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    private Boolean passwordsMatches(String newPassword,
                                     String confirmNewPassword) {
        return newPassword.equals(confirmNewPassword);
    }

    private Boolean isTokenExpired(Instant expirationTime) {
        return Instant.now().isAfter(expirationTime);
    }
}
