package com.edoardoconti.kmz_backend.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRefreshDto {
    private String refreshToken;
}
