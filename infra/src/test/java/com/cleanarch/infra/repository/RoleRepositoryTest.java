package com.cleanarch.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.cleanarch.infra.config.testcontainer.AbstractTestContainerConfig;
import com.cleanarch.infra.domain.model.Role;
import java.util.Collections;
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

  @DisplayName("Test given Role object when save should return Role object")
  @Test
  void testGivenRoleObject_WhenSave_ThenShouldReturnRoleObject() {

    // Given / Arrange

    // When / Act
    Role savedUserRole = roleRepository.save(ROLE_USER);
    Role savedAdminRole = roleRepository.save(ROLE_ADMIN);

    // Then / Assert
    assertNotNull(savedUserRole);
    assertNotNull(savedAdminRole);
    assertTrue(savedUserRole.getId() > 0);
    assertTrue(savedAdminRole.getId() > 0);
    assertEquals(ROLE_USER.getName(), savedUserRole.getName());
    assertEquals(ROLE_ADMIN.getName(), savedAdminRole.getName());
  }

  @DisplayName(
      "Test given role list when list all then should return a list of Role"
          + " objects")
  @Test
  void testGivenRoleList_WhenListAll_ThenShouldReturnAListOfRoleObjects() {

    // Given / Arrange
    List<Role> givenRoles =
        Collections.unmodifiableList(List.of(ROLE_USER, ROLE_ADMIN));

    roleRepository.saveAll(givenRoles);

    // When / Act

    List<Role> rolesList = roleRepository.findAll();

    // Then / Assert
    assertNotNull(rolesList);
    assertEquals(givenRoles.size(), rolesList.size());
  }
}
