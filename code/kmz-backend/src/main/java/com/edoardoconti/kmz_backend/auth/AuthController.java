package com.edoardoconti.kmz_backend.auth;

import com.edoardoconti.kmz_backend.user.UserLoginDto;
import com.edoardoconti.kmz_backend.user.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        var user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();

        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok().build();
    }


}
