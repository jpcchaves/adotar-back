package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.UserSavedPets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSavedPetsRepository extends JpaRepository<UserSavedPets, Long> {
    List<UserSavedPets> findAllByUserId(Long userId);
}
