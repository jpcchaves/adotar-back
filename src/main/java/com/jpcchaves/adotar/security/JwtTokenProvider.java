package com.jpcchaves.adotar.security;

import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.BadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + jwtExpiration);

        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key())
                .claim("authorities", authentication.getAuthorities())
                .claim("admin", user.getAdmin())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;

        } catch (MalformedJwtException ex) {
            throw new BadRequestException("Token inválido!");
        } catch (ExpiredJwtException ex) {
            throw new BadRequestException("Token expirado!");
        } catch (UnsupportedJwtException ex) {
            throw new BadRequestException("O tipo do token é inválido!");
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("O token é obrigatório para acessar esse recurso!");
        }
    }
}
