package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(this.service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody UserSignUpRequestDTO request) {

        List<UserRole> roles = Arrays.stream(request.userRoles())
                .map(UserRoleType::create)
                .toList();

        this.service.signUp(request.user(), roles);
        return ResponseEntity.status(201).build();
    }
}


