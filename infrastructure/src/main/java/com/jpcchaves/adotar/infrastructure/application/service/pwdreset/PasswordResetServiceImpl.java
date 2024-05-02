package com.jpcchaves.adotar.infrastructure.application.service.pwdreset;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.PasswordResetRequestDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.PasswordResetTokenRequestDto;
import com.jpcchaves.adotar.infrastructure.application.service.pwdreset.contracts.PasswordResetService;
import com.jpcchaves.adotar.infrastructure.application.service.pwdreset.contracts.PwdResetUtils;
import com.jpcchaves.adotar.infrastructure.domain.exception.PasswordsMismatchException;
import com.jpcchaves.adotar.infrastructure.domain.exception.TokenExpiredException;
import com.jpcchaves.adotar.infrastructure.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.infrastructure.domain.model.User;
import com.jpcchaves.adotar.infrastructure.infra.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

  private final UserRepository userRepository;
  private final PwdResetUtils pwdResetUtils;

  public PasswordResetServiceImpl(
      UserRepository userRepository, PwdResetUtils pwdResetUtils) {
    this.userRepository = userRepository;
    this.pwdResetUtils = pwdResetUtils;
  }

  @Override
  public ApiMessageResponseDto resetTokenRequestDto(
      PasswordResetRequestDto passwordResetRequestDto)
      throws MessagingException {
    User user =
        pwdResetUtils.getUserByEmail(passwordResetRequestDto.getEmail());

    PasswordResetToken token = pwdResetUtils.generatePasswordResetToken(user);
    pwdResetUtils.sendPasswordRequest(token);

    return new ApiMessageResponseDto("Solicitação enviada com sucesso!");
  }

  @Override
  public ApiMessageResponseDto resetPassword(
      PasswordResetTokenRequestDto passwordResetTokenRequestDto) {
    String newPassword = passwordResetTokenRequestDto.getNewPassword();
    String confirmNewPassword =
        passwordResetTokenRequestDto.getConfirmNewPassword();

    if (!pwdResetUtils.passwordsMatch(newPassword, confirmNewPassword)) {
      throw new PasswordsMismatchException(
          "As senhas não são indênticas. Verifique os dados informados e tente novamente");
    }

    PasswordResetToken passwordResetToken =
        pwdResetUtils.getPasswordResetToken(
            passwordResetTokenRequestDto.getToken());

    if (pwdResetUtils.isTokenExpired(passwordResetToken.getExpirationTime())) {
      pwdResetUtils.deleteExpiredToken(passwordResetToken);
      throw new TokenExpiredException(
          "O token informado não existe ou está expirado. Verifique os dados informados ou solicite um novo token para continuar");
    }

    User user =
        pwdResetUtils.getUserByEmail(passwordResetToken.getUser().getEmail());
    user.setPassword(
        pwdResetUtils.encodePassword(
            passwordResetTokenRequestDto.getNewPassword()));
    userRepository.save(user);

    pwdResetUtils.deleteExpiredToken(passwordResetToken);

    return new ApiMessageResponseDto("Senha redefinida com sucesso!");
  }
}
