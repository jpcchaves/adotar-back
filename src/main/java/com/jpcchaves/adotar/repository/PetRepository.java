package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> getAllByBreed_IdAndType_Id(Pageable pageable,
                                         Long breedId,
                                         Long typeId);

    Page<Pet> getAllByType_Id(Pageable pageable,
                              Long typeId);

    Page<Pet> getAllByUser_Id(Pageable pageable,
                              Long userId);

    Page<Pet> findAllByActiveTrue(Pageable pageable);

    Optional<Pet> findByIdAndActiveTrue(Long id);

    Optional<Pet> findBySerialNumberAndActiveTrue(String serialNumber);
}
