package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.PetPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetPictureRepository extends JpaRepository<PetPicture, Long> {
    List<PetPicture> getAllByPet_Id(Long petId);

    Optional<PetPicture> getByIdAndPet_Id(Long picId,
                                          Long petId);
}
