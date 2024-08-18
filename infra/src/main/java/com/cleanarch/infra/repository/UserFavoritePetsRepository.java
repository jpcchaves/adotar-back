package com.cleanarch.infra.repository;

import com.cleanarch.infra.domain.model.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

@Repository
public interface UserFavoritePetsRepository
    extends JpaRepository<UserFavoritePets, Long> {

  @Query(
      value =
          "SELECT * FROM user_favorite_pets ufp WHERE ufp.user_id = :userId",
      nativeQuery = true)
  List<UserFavoritePets> findAllByUser(@Param("userId") Long userId);

  @Query(
      value =
          "SELECT * FROM user_favorite_pets ufp where ufp.pet_id = :petId AND"
              + " ufp.user_id = :userId",
      nativeQuery = true)
  Optional<UserFavoritePets> findByUserAndPet(
      @Param("userId") Long userId, @Param("petId") Long petId);

  @Query(
      value =
          "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM"
              + " user_favorite_pets ufp WHERE ufp.pet_id = :petId and"
              + " ufp.user_id = :userId",
      nativeQuery = true)
  boolean existsByPetAndUser(
      @Param("petId") Long petId, @Param("userId") Long userId);
}
