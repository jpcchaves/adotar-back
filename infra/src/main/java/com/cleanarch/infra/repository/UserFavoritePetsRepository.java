package com.cleanarch.infra.repository;

import com.cleanarch.infra.domain.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserFavoritePetsRepository
    extends JpaRepository<UserFavoritePets, Long> {}
