package com.jpcchaves.adotar.config.auditing;

import com.jpcchaves.adotar.domain.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class SpringJpaAuditingConfig implements AuditorAware<Long> {

  @Override
  @NonNull
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(
            SecurityContextHolder.getContext()
        )
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(authentication -> {
          User user = (User) authentication.getPrincipal();
          return user.getId();
        });
  }
}
