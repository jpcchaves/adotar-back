package com.jpcchaves.adotar.infra.repository;

import com.jpcchaves.adotar.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByNameOrUf(String name,
                                   String uf);
}
