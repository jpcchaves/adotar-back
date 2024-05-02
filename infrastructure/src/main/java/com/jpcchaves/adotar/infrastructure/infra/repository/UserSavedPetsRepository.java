package com.jpcchaves.adotar.infrastructure.infra.repository;

import com.jpcchaves.adotar.infrastructure.domain.model.UserSavedPets;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSavedPetsRepository
    extends JpaRepository<UserSavedPets, Long> {
  List<UserSavedPets> findAllByUserId(Long userId);

  Optional<UserSavedPets> findByPet_IdAndUser_Id(Long petId, Long userId);

  boolean existsByPet_IdAndUser_Id(Long petId, Long userId);
}
