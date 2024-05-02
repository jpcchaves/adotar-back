package com.jpcchaves.adotar.infrastructure.infra.repository;

import com.jpcchaves.adotar.infrastructure.domain.model.PetCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCharacteristicRepository
    extends JpaRepository<PetCharacteristic, Long> {}
