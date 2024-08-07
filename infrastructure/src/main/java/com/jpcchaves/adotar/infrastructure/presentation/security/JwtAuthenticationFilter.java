package com.jpcchaves.adotar.infrastructure.presentation.security;

import br.com.jpcchaves.core.exception.AuthException;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.jpcchaves.adotar.infrastructure.application.service.jwt.contracts.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(
      JwtTokenProvider jwtTokenProvider,
      UserDetailsService userDetailsService
  ) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  )
      throws ServletException, IOException {
    String token = getTokenFromRequest(request);

    if (isTokenValid(token)) {
      String username = jwtTokenProvider.getUsernameFromToken(token);
      UserDetails userDetails =
          this.userDetailsService.loadUserByUsername(username);

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());

      authenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {

    String token = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (checkIfHeaderHasBearerToken(token)) {
      return token.substring(7);
    }

    throw new AuthException(ExceptionDefinition.JWT0004);
  }

  private Boolean checkIfHeaderHasBearerToken(String token) {
    return StringUtils.hasText(token) && token.startsWith("Bearer ");
  }

  private Boolean isTokenValid(String token) {
    return StringUtils.hasText(token) && jwtTokenProvider.validateToken(token);
  }
}
