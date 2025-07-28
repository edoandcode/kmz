package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

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
    public ResponseEntity<Void> signUp(@RequestBody User user, UserRole[] userRoles) {
        this.service.signUp(user, userRoles);
        return ResponseEntity.status(201).build();
    }
}


