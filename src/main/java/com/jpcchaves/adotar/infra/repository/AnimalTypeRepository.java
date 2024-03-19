package com.jpcchaves.adotar.infra.repository;

import com.jpcchaves.adotar.domain.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {}
