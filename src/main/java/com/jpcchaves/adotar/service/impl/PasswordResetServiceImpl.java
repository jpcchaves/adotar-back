package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.PasswordResetToken;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.repository.PasswordResetTokenRepository;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.service.usecases.EmailService;
import com.jpcchaves.adotar.service.usecases.PasswordResetService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    private static final int TOKEN_VALIDITY_MINUTES = 10;

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public PasswordResetServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository,
                                    UserRepository userRepository,
                                    EmailService emailService) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    @Override
    public ApiMessageResponseDto resetTokenRequestDto(PasswordResetRequestDto passwordResetRequestDto) throws MessagingException {
        User user = userRepository
                .findByEmail(passwordResetRequestDto.getEmail())
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado com o email informado. Por favor, verifique o email e tente novamente"));

        String token = generateRandomCode();
        Instant expirationDate = calculateExpirationDate();

        PasswordResetToken newPasswordResetToken = new PasswordResetToken(token, expirationDate, user);

        PasswordResetToken savedPasswordResetToken = passwordResetTokenRepository.save(newPasswordResetToken);

        emailService.sendPasswordRequest(savedPasswordResetToken);

        return new ApiMessageResponseDto("Solicitação enviada com sucesso!");
    }

    private Instant calculateExpirationDate() {
        return Instant.now().plus(TOKEN_VALIDITY_MINUTES, ChronoUnit.MINUTES);
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
