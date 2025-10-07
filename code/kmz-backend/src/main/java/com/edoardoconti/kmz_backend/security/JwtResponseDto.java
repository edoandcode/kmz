package com.edoardoconti.kmz_backend.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;

    public JwtResponseDto(String accessToken) {
        this.accessToken = accessToken;
        this.refreshToken = null;
    }
}
