package com.cleanarch.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.domain.model.User;
import java.util.Date;
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
class UserRepositoryTest extends AbstractTestContainerConfig {

  @Autowired private UserRepository userRepository;

  private Faker faker = new Faker();

  private User user;

  private final Date createdAt = new Date();

  private final Date updatedAt = new Date();

  @BeforeEach
  void setUp() {

    user =
        new User(
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
    assertTrue(savedUser.getId() > 0);
    assertEquals(user.getFirstName(), savedUser.getFirstName());
    assertEquals(user.getLastName(), savedUser.getLastName());
    assertEquals(user.getEmail(), savedUser.getEmail());
    assertEquals(user.getPassword(), savedUser.getPassword());
    assertEquals(user.getCreatedAt(), savedUser.getCreatedAt());
    assertEquals(user.getUpdatedAt(), savedUser.getUpdatedAt());
  }

  @DisplayName("Test Given User Id When Search By Id Should Return User Object")
  @Test
  void testGivenUserId_WhenSearchById_ShouldReturnUserObject() {

    // Given / Arrange
    User savedUser = userRepository.save(user);
    Long userId = savedUser.getId();

    // When / Act
    User user = userRepository.findById(userId).get();

    // Then / Assert
    assertNotNull(user);
  }

  @DisplayName(
      "Test given user email when search by email should return user object")
  @Test
  void testGivenUserEmail_WhenSearchByEmail_ShouldReturnUserObject() {

    // Given / Arrange
    User savedUser = userRepository.save(user);
    String email = savedUser.getEmail();

    // When / Act
    User user = userRepository.findByEmail(email).get();

    // Then / Assert
    assertNotNull(user);
  }

  @DisplayName(
      "Test given user when update user info should return updated user object")
  @Test
  void testGivenUser_WhenUpdateUser_ShouldReturnUpdatedUserObject() {

    // Given / Arrange
    User savedUser = userRepository.save(user);

    String updatedEmail = faker.internet().emailAddress();
    String updatedFirstName = faker.name().firstName();
    String updatedLastName = faker.name().lastName();

    // When / Act
    savedUser.setEmail(updatedEmail);
    savedUser.setLastName(updatedLastName);
    savedUser.setFirstName(updatedFirstName);

    User updatedUser = userRepository.save(savedUser);

    // Then / Assert
    assertNotNull(updatedUser);
    assertEquals(updatedEmail, updatedUser.getEmail());
    assertEquals(updatedFirstName, updatedUser.getFirstName());

    assertEquals(updatedLastName, updatedUser.getLastName());
    assertEquals(updatedUser.getId(), savedUser.getId());
  }

  @DisplayName(
      "Test given user email when verify if user existsByEmail then should"
          + " return true")
  @Test
  void testGivenUserEmail_WhenVerifyIfUserExistsByEmail_ThenShouldReturnTrue() {

    // Given / Arrange
    User savedUser = userRepository.save(user);

    // When / Act
    Boolean exists = userRepository.existsByEmail(savedUser.getEmail());

    // Then / Assert
    assertTrue(exists);
  }
}
