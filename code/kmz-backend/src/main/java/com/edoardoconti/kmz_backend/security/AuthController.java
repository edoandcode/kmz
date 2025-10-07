package com.edoardoconti.kmz_backend.security;

import com.edoardoconti.kmz_backend.user.UserDto;
import com.edoardoconti.kmz_backend.user.UserLoginRequestDto;
import com.edoardoconti.kmz_backend.user.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtConfig jwtConfig;
    private final UserMapper userMapper;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(
            @Valid @RequestBody UserLoginRequestDto userLoginRequestDto,
            HttpServletResponse httpServletResponse
    ) {

       var loginResponse = this.authService.login(userLoginRequestDto);

       var accessToken = loginResponse.getAccessToken().toString();
       var refreshToken = loginResponse.getRefreshToken().toString();

       return ResponseEntity.ok(new JwtResponseDto(accessToken, refreshToken));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(){
        var user = this.authService.getCurrentUser();
        if(user == null)
            return ResponseEntity.notFound().build();

        var userDto = this.userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refresh(@CookieValue(value = "refreshToken") String refreshToken){
        var accessJwt = this.authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(new JwtResponseDto(accessJwt.toString()));
    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
