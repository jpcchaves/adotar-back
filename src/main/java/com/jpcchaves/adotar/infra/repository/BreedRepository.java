package com.jpcchaves.adotar.infra.repository;

import com.jpcchaves.adotar.domain.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
    List<Breed> findAllByAnimalType_Id(Long animalTypeId);

    Optional<Breed> findByIdAndAnimalType_Id(Long id,
                                             Long animalTypeId);
}
