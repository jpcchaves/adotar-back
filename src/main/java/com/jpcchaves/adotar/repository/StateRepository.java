package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByName(String name);

    Optional<State> findByUf(String uf);
}
