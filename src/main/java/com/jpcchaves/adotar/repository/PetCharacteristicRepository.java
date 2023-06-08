package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.PetCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCharacteristicRepository extends JpaRepository<PetCharacteristic, Long> {
}
