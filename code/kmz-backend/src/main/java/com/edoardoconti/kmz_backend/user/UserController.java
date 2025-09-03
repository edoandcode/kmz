package com.edoardoconti.kmz_backend.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name="Users")
public class UserController {

    private final UserService service;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(this.service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        this.service.registerUser(userRegisterDto);
        return ResponseEntity.status(201).build();
    }
}


