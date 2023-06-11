package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {
}
