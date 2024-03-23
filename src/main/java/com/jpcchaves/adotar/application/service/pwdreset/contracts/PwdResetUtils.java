package com.jpcchaves.adotar.application.service.pwdreset.contracts;

import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.domain.model.User;
import jakarta.mail.MessagingException;
import java.time.Instant;

public interface PwdResetUtils {
  PasswordResetToken generatePasswordResetToken(User user);

  PasswordResetToken getPasswordResetToken(String token);

  User getUserByEmail(String email);

  String encodePassword(String rawPassword);

  void sendPasswordRequest(PasswordResetToken token) throws MessagingException;

  Instant calculateExpirationDate();

  void deleteExpiredToken(PasswordResetToken token);

  String generateRandomCode();

  PasswordResetToken buildNewToken(User user);

  Boolean passwordsMatch(String newPassword, String confirmNewPassword);

  Boolean isTokenExpired(Instant expirationTime);
}
