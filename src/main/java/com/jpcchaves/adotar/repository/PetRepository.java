package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> getAllByBreed_IdAndType_Id(
            Pageable pageable,
            Long breedId,
            Long typeId);

    Page<Pet> getAllByType_Id(
            Pageable pageable,
            Long typeId);

    Page<Pet> getAllByBreed_Id(
            Pageable pageable,
            Long breedId
    );

    @Query("SELECT p FROM Pet p WHERE p.type.id IN :typeIds AND p.active = true")
    Page<Pet> findByTypes(
            Pageable pageable,
            @Param("typeIds") List<Long> typeIds);

    @Query(value = "SELECT * FROM Pet p WHERE LOWER(REGEXP_REPLACE(p.name, '[^a-zA-Z0-9]', '')) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<Pet> findAllByNameContainingIgnoreCase();

    Page<Pet> getAllByUser_Id(
            Pageable pageable,
            Long userId);

    Page<Pet> findAllByActiveTrue(Pageable pageable);

    Optional<Pet> findByIdAndActiveTrue(Long id);

    Optional<Pet> findBySerialNumberAndActiveTrue(String serialNumber);
}
