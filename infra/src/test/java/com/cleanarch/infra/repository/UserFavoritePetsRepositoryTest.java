package com.cleanarch.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.com.jpcchaves.core.domain.enums.AnimalSize;
import br.com.jpcchaves.core.domain.enums.AnimalType;
import br.com.jpcchaves.core.domain.enums.Gender;
import br.com.jpcchaves.core.domain.enums.HealthCondition;
import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.domain.model.Pet;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.domain.model.UserFavoritePets;
import java.util.List;
import java.util.Optional;
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
class UserFavoritePetsRepositoryTest extends AbstractTestContainerConfig {

  private User user;
  private Pet pet;

  private Faker faker;

  @Autowired private PetRepository petRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private UserFavoritePetsRepository userFavoritePetsRepository;

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

    pet =
        petRepository.save(
            new Pet(
                faker.dog().name(),
                1,
                2,
                Gender.MALE,
                AnimalSize.LARGE,
                HealthCondition.HEALTHY,
                AnimalType.DOG,
                faker.color().name(),
                faker.text().text(20),
                faker.dog().breed(),
                user));
  }

  @DisplayName(
      "Test given User when find all by User then should return Pets created by"
          + " the  user")
  @Test
  void testGivenUser_WhenFindAllByUser_ThenShouldReturnPetsCreatedByTheUser() {

    // Given / Arrange
    userFavoritePetsRepository.save(new UserFavoritePets(user, pet));

    // When / Act
    List<UserFavoritePets> userFavoritePetsList =
        userFavoritePetsRepository.findAllByUser(user.getId());

    // Then / Assert
    assertNotNull(userFavoritePetsList);

    assertEquals(1, userFavoritePetsList.size());

    assertEquals(user.getId(), userFavoritePetsList.get(0).getUser().getId());
  }

  @DisplayName(
      "Test given User and Pet when find by user and pet then should return"
          + " UserFavoritePets Object")
  @Test
  void
      testGivenUserAndPet_WhenFindByUserAndPet_ThenShouldReturnUserFavoritePetObject() {

    // Given / Arrange
    userFavoritePetsRepository.save(new UserFavoritePets(user, pet));

    // When / Act
    Optional<UserFavoritePets> userFavoritePet =
        userFavoritePetsRepository.findByUserAndPet(user.getId(), pet.getId());

    // Then / Assert
    assertNotNull(userFavoritePet.get());
    assertEquals(user.getId(), userFavoritePet.get().getUser().getId());
    assertEquals(pet.getId(), userFavoritePet.get().getPet().getId());
  }

  @DisplayName(
      "Test given User and Pet when exists by User and Pet then should return"
          + " true")
  @Test
  void testGivenUserAndPet_WhenExistsByUserAndPet_ThenShouldReturnTrue() {

    // Given / Arrange
    userFavoritePetsRepository.save(new UserFavoritePets(user, pet));

    // When / Act
    boolean result =
        userFavoritePetsRepository.existsByUserAndPet(
            user.getId(), pet.getId());

    // Then / Assert
    assertTrue(result);
  }
}
