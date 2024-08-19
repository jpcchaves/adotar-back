package com.cleanarch.infra.util;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PasswordResetTokenUtils {

  private static final int FIVE_MINUTES = 5;

  public static String generateToken() {

    SecureRandom random = new SecureRandom();

    int token = random.nextInt(900000) + 100000;

    return String.valueOf(token);
  }

  public static Instant generateExpirationTime() {

    return Instant.now().plus(FIVE_MINUTES, ChronoUnit.MINUTES);
  }
}
