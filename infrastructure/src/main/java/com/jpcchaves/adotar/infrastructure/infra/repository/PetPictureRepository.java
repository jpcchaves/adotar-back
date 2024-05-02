package com.jpcchaves.adotar.infrastructure.infra.repository;

import com.jpcchaves.adotar.infrastructure.domain.model.PetPicture;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetPictureRepository extends JpaRepository<PetPicture, UUID> {}
