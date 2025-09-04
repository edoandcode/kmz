package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public String generateAccessToken(User user) {
        return generateToken(user, this.jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, this.jwtConfig.getRefreshTokenExpiration());
    }


    public boolean validateToken(String token) {
        try {
            var claims = this.getClaims(token);
            return claims.getExpiration().after(new Date());
        }catch(JwtException ex) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        return Long.valueOf(this.getClaims(token).getSubject());
    }


    // private methods

    private Claims getClaims(String token) {
        // claims are properties we know about the jwt token, appearing as key/value pairs
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(this.jwtConfig.getSecret().getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private String generateToken(User user, long tokenExpiration) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("fullName", user.getFirstName() + " " + user.getLastName())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .signWith(Keys.hmacShaKeyFor(this.jwtConfig.getSecret().getBytes()))
                .compact();
    }


}
