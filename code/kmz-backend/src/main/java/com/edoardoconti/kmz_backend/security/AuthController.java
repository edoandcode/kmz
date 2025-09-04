package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.user.UserDto;
import com.edoardoconti.kmz_backend.user.UserLoginDto;
import com.edoardoconti.kmz_backend.user.UserMapper;
import com.edoardoconti.kmz_backend.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(
            @Valid @RequestBody UserLoginDto userLoginDto,
            HttpServletResponse httpServletResponse
    ) {

       this.authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       userLoginDto.getEmail(),
                       userLoginDto.getPassword()
               )
       );

       var user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow();

       var accessToken = this.jwtService.generateAccessToken(user);
       var refreshToken = this.jwtService.generateRefreshToken(user);

       var cookie = new Cookie("refreshToken", refreshToken);
       cookie.setHttpOnly(true);
       cookie.setPath("/auth/refresh");
       cookie.setMaxAge(this.jwtConfig.getRefreshTokenExpiration());
       cookie.setSecure(true);

       httpServletResponse.addCookie(cookie);

       return ResponseEntity.ok(new JwtResponseDto(accessToken));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var id = (Long) authentication.getPrincipal();
        var user = this.userRepository.findById(id).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        var userDto = this.userMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refresh(@CookieValue(value = "refreshToken") String refreshToken){
        if(!this.jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var userId = this.jwtService.getUserIdFromToken(refreshToken);
        var user = this.userRepository.findById(userId).orElseThrow();
        var accessToken = this.jwtService.generateAccessToken(user);

        return ResponseEntity.ok(new JwtResponseDto(accessToken));
    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
