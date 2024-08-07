package com.jpcchaves.adotar.infrastructure.infra.repository;

import com.jpcchaves.adotar.infrastructure.domain.model.State;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
  Optional<State> findByNameOrUf(String name, String uf);
}
