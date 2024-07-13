package com.cleanarch.infra.config.security;

import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;
import com.cleanarch.infra.domain.model.User;
import com.cleanarch.usecase.auth.dto.UserMinDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

  private static final String AUTHORITIES_CLAIM_KEY = "authorities";
  private static final String USER_CLAIM_KEY = "user";

  private static final Logger logger = LoggerFactory.getLogger(
      JwtTokenProviderImpl.class);

  @Value("${app.jwt-secret}")
  private String jwtSecret;

  @Value("${app.jwt-expiration-milliseconds}")
  private long jwtExpiration;

  @Override
  public String generateToken(Authentication authentication) {

    User user = (User) authentication.getPrincipal();

    Date creationDate = new Date();

    Date expirationDate = new Date(creationDate.getTime() + jwtExpiration);

    return Jwts
        .builder()
        .setSubject(user.getEmail())
        .setIssuedAt(creationDate)
        .setExpiration(expirationDate)
        .signWith(generateKey())
        .claim(AUTHORITIES_CLAIM_KEY, authentication.getAuthorities())
        .claim(USER_CLAIM_KEY, generateUserClaims(user))
        .compact();
  }

  @Override
  public String getTokenSubject(String token) {

    Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(generateKey())
        .build()
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

  @Override
  public boolean validateToken(String token) {

    try {

      Jwts
          .parserBuilder()
          .setSigningKey(generateKey())
          .build()
          .parse(token);

      return true;

    } catch (MalformedJwtException ex) {

      throw new BadRequestException(ExceptionDefinition.JWT0001);
    } catch (ExpiredJwtException ex) {

      throw new BadRequestException(ExceptionDefinition.JWT0002);
    } catch (UnsupportedJwtException ex) {

      throw new BadRequestException(ExceptionDefinition.JWT0003);
    } catch (IllegalArgumentException ex) {

      throw new BadRequestException(ExceptionDefinition.JWT0004);
    } catch (SecurityException ex) {

      throw new BadRequestException(ExceptionDefinition.JWT0005);
    }
  }

  @Override
  public String getClaimFromTokenByKey(
      String token,
      String key
  ) {

    try {

      Map<String, Object> claims =
          Jwts.parserBuilder()
              .setSigningKey(generateKey())
              .build()
              .parseClaimsJws(token)
              .getBody();

      ObjectMapper objectMapper = new ObjectMapper();

      Map<String, Object> userClaim =
          objectMapper.convertValue(
              claims.get(USER_CLAIM_KEY), new TypeReference<>() {
              });

      return String.valueOf(userClaim.get(key));

    } catch (Exception e) {

      logger.error("Could not get all claims Token from passed token");

      return null;
    }
  }

  private Key generateKey() {

    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  private UserMinDTO generateUserClaims(User user) {
    return new UserMinDTO(
        user.getId(),
        user.getFirstName() + " " + user.getLastName(),
        user.getEmail());
  }
}
