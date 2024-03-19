package com.jpcchaves.adotar.infra.repository;

import com.jpcchaves.adotar.domain.model.PasswordResetToken;
import com.jpcchaves.adotar.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository
    extends JpaRepository<PasswordResetToken, Long> {
  Optional<PasswordResetToken> findByToken(String token);

  Optional<PasswordResetToken> findByUser(User user);
}
