package com.cleanarch.infra.repository;

import com.cleanarch.infra.domain.model.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PasswordResetTokenRepository
    extends JpaRepository<PasswordResetToken, Long> {

  @Query(
      value = "SELECT * FROM password_reset_token WHERE user_id = :userId",
      nativeQuery = true)
  Optional<PasswordResetToken> findByUser(Long userId);
}
