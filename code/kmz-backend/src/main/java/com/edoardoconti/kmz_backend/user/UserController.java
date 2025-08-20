package com.edoardoconti.kmz_backend.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(this.service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpUserRequest request) {
        this.service.signUp(request);
        return ResponseEntity.status(201).build();
    }
}


