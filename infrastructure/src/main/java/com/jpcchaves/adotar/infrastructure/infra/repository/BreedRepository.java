package com.jpcchaves.adotar.infrastructure.infra.repository;

import com.jpcchaves.adotar.infrastructure.domain.model.Breed;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
  List<Breed> findAllByAnimalType_Id(Long animalTypeId);

  Optional<Breed> findByIdAndAnimalType_Id(Long id, Long animalTypeId);
}
