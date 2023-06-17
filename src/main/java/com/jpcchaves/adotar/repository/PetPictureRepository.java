package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.PetPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetPictureRepository extends JpaRepository<PetPicture, Long> {
}
