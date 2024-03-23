package com.jpcchaves.adotar.application.service.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpcchaves.adotar.application.dto.user.UserMinDto;
import com.jpcchaves.adotar.application.service.jwt.contracts.JwtTokenProvider;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import com.jpcchaves.adotar.domain.model.User;
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
    private static final Logger logger =
            LoggerFactory.getLogger(JwtTokenProviderImpl.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication) {
        final String AUTHORITIES_CLAIM_KEY = "authorities";
        final String USER_CLAIM_KEY = "user";
        User user = (User) authentication.getPrincipal();

        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(generateKey())
                .claim(AUTHORITIES_CLAIM_KEY, authentication.getAuthorities())
                .claim(USER_CLAIM_KEY, generateUserClaims(user))
                .compact();
    }

    private UserMinDto generateUserClaims(User user) {
        return new UserMinDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getFirstName() + " " + user.getLastName(),
                user.getUsername(),
                user.getEmail());
    }

    private Key generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsernameFromToken(String token) {
        Claims claims =
                Jwts.parserBuilder()
                        .setSigningKey(generateKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

        return claims.getSubject();
    }

    @Override
    public String getClaimFromTokenByKey(String token,
                                         String key) {
        final String USER_CLAIM_KEY = "user";

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

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(generateKey()).build().parse(token);

            return true;

        } catch (MalformedJwtException ex) {
            throw new BadRequestException("Token inválido!");
        } catch (ExpiredJwtException ex) {
            throw new BadRequestException("Token expirado!");
        } catch (UnsupportedJwtException ex) {
            throw new BadRequestException("O tipo do token é inválido!");
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(
                    "O token é obrigatório para acessar esse recurso!");
        } catch (SecurityException ex) {
            throw new BadRequestException("A assinatura do token é inválido!");
        }
    }
}
