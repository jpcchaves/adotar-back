package com.cleanarch.infra.repository;

import com.cleanarch.infra.domain.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query(value = "SELECT * FROM roles r WHERE UPPER(r.name) = UPPER(:name)", nativeQuery = true)
  Optional<Role> findByName(String name);
}
