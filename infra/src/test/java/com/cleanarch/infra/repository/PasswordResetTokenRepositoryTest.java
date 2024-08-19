package com.cleanarch.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.domain.model.PasswordResetToken;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.util.PasswordResetTokenUtils;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(
    locations = {"classpath:application-test.yml", "classpath:.env"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasswordResetTokenRepositoryTest extends AbstractTestContainerConfig {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordResetTokenRepository passwordResetTokenRepository;

  private User user;

  private Faker faker;

  @BeforeEach
  void setup() {

    faker = new Faker();

    user =
        userRepository.save(
            new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                Boolean.TRUE));
  }

  @DisplayName(
      "Test given user id when find by user then should return"
          + " PasswordResetToken object")
  @Test
  void
      testGivenUserId_WhenFindByUser_ThenShouldReturnPasswordResetTokenObject() {

    // Given / Arrange
    passwordResetTokenRepository.save(
        new PasswordResetToken(
            PasswordResetTokenUtils.generateToken(),
            PasswordResetTokenUtils.generateExpirationTime(),
            user));

    // When / Act

    PasswordResetToken passwordResetToken =
        passwordResetTokenRepository.findByUser(user.getId()).get();

    // Then / Assert
    assertNotNull(passwordResetToken);
  }
}
