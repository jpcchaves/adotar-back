package com.jpcchaves.adotar.infra.repository;

import com.jpcchaves.adotar.domain.model.PetCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCharacteristicRepository extends JpaRepository<PetCharacteristic, Long> {
}
