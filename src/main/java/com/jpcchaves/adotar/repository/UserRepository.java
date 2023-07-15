package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username,
                                         String email);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
