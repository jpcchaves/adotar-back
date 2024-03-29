package com.jpcchaves.adotar.infra.repository;

import com.jpcchaves.adotar.domain.model.PetPicture;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetPictureRepository extends JpaRepository<PetPicture, UUID> {}
