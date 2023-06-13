package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
    List<Breed> findAllByAnimalType_Id(Long animalTypeId);
}
