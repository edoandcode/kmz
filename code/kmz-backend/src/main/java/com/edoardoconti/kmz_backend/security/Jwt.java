package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.role.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;


    public boolean isValid() {
        return this.claims.getExpiration().after(new Date());
    }

    public Long getUserId() {
        return Long.valueOf(this.claims.getSubject());
    }

    public Set<UserRole> getRoles() {
        List<String> roles = this.claims.get("roles", List.class);
        return roles.stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString(){
        return Jwts.builder().claims(this.claims).signWith(this.secretKey).compact();
    }



}
