package com.cleanarch.infra.repository;

import com.cleanarch.infra.domain.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(
      value = "SELECT * FROM users u WHERE u.email = :email",
      nativeQuery = true)
  Optional<User> findByEmail(@Param("email") String email);

  @Query(
      value = "SELECT EXISTS(SELECT 1 FROM users u WHERE u.email = :email)",
      nativeQuery = true)
  Boolean existsByEmail(@Param("email") String email);
}
