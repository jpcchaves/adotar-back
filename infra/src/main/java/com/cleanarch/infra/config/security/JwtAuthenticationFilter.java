package com.cleanarch.infra.config.security;

import com.cleanarch.infra.domain.model.User;
import com.cleanarch.infra.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;
  private final JwtUtils jwtUtils;

  public JwtAuthenticationFilter(
      JwtTokenProvider jwtTokenProvider,
      UserService userService,
      JwtUtils jwtUtils
  ) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userService = userService;
    this.jwtUtils = jwtUtils;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    String token = jwtUtils.getTokenFromRequest(request);

    if (jwtUtils.isTokenValid(token)) {

      String tokenSubject = jwtTokenProvider.getTokenSubject(token);

      User user = userService.getUserByEmail(tokenSubject);

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(user, null,
              user.getAuthorities());

      authenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }
}
