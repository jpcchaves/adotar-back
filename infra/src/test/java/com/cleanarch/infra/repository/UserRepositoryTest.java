package com.cleanarch.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.cleanarch.infra.domain.model.User;
import java.util.Date;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  private Faker faker = new Faker();

  private User user;

  private final Date createdAt = new Date();
  private final Date updatedAt = new Date();

  @BeforeEach
  void setUp() {

    user =
        new User(
            faker.number().randomNumber(),
            faker.name().firstName(),
            faker.name().lastName(),
            faker.internet().emailAddress(),
            faker.random().hex(12),
            Boolean.TRUE,
            createdAt,
            updatedAt);
  }

  @DisplayName("Test given user when saving should return an User object")
  @Test
  void testGivenUser_WhenSaving_ShouldReturnUserObject() {

    // Given / Arrange

    // When / Act
    User savedUser = userRepository.save(user);

    // Then / Assert
    assertNotNull(savedUser);
    assertEquals(user.getId(), savedUser.getId());
  }
}
