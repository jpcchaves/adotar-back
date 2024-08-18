package com.cleanarch.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.domain.model.Role;
import java.util.List;
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
class RoleRepositoryTest extends AbstractTestContainerConfig {

  @Autowired private RoleRepository roleRepository;

  private Role ROLE_USER;
  private Role ROLE_ADMIN;

  @BeforeEach
  void setup() {

    ROLE_USER = new Role(1L, "ROLE_USER");
    ROLE_ADMIN = new Role(2L, "ROLE_ADMIN");
  }

  @DisplayName(
      "Test given role name when find by name then should return Role object")
  @Test
  void testGivenRoleName_WhenFindByName_ThenShouldReturnRoleObject() {

    // Given / Arrange
    roleRepository.saveAll(List.of(ROLE_USER, ROLE_ADMIN));

    // When / Act
    Role fetchedRole = roleRepository.findByName("ROLE_USER").get();

    // Then / Assert
    assertNotNull(fetchedRole);
    assertTrue(fetchedRole.getId() > 0);
    assertEquals(ROLE_USER.getName(), fetchedRole.getName());
  }
}
