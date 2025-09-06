package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.security.Jwt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginResponseDto {
    private Jwt accessToken;
    private Jwt refreshToken;
}
