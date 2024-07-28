package com.cleanarch.infra.repository;

import com.cleanarch.infra.domain.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query(
      value = "SELECT * FROM roles r WHERE UPPER(r.name) = UPPER(:name)",
      nativeQuery = true)
  Optional<Role> findByName(String name);
}
