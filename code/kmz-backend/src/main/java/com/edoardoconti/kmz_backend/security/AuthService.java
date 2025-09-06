package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.user.User;
import com.edoardoconti.kmz_backend.user.UserLoginRequestDto;
import com.edoardoconti.kmz_backend.user.UserLoginResponseDto;
import com.edoardoconti.kmz_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var id = (Long) authentication.getPrincipal();
        return this.userRepository.findById(id).orElse(null);
    }

    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.getEmail(),
                        userLoginRequestDto.getPassword()
                )
        );

        var user = this.userRepository.findByEmail(userLoginRequestDto.getEmail()).orElseThrow();
        var accessJwt = this.jwtService.generateAccessToken(user);
        var refreshJwt = this.jwtService.generateRefreshToken(user);

        return new UserLoginResponseDto(accessJwt, refreshJwt);
    }

    public Jwt refreshAccessToken(String refreshToken) {
        var jwt = this.jwtService.parseToken(refreshToken);
        if(jwt == null || !jwt.isValid())
            throw new BadCredentialsException("Invalid refresh token");

        var user = this.userRepository.findById(jwt.getUserId()).orElseThrow();
        return this.jwtService.generateAccessToken(user);
    }

}
