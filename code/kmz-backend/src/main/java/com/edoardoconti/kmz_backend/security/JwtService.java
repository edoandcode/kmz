package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public Jwt generateAccessToken(User user) {
        return generateToken(user, this.jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, this.jwtConfig.getRefreshTokenExpiration());
    }

    public Jwt parseToken(String token) {
        try {
            var claims = this.getClaims(token);
            return new Jwt(claims, this.jwtConfig.getSecretKey());
        } catch(JwtException ex) {
            return null;
        }
    }


    // private methods

    private Jwt generateToken(User user, long tokenExpiration) {
        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("fullName", user.getFirstName() + " " + user.getLastName())
                .add("email", user.getEmail())
                .add("roles", user.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();

        return new Jwt(claims, this.jwtConfig.getSecretKey());
    }

    private Claims getClaims(String token) {
        // claims are properties we know about the jwt token, appearing as key/value pairs
        return Jwts.parser()
                .verifyWith(this.jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
