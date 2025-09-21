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

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        this.userService.registerUser(userRegisterDto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("setup-admin")
    public ResponseEntity<UserDto> registerAdmin(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        this.userService.registerAdmin(userRegisterDto);
        return ResponseEntity.status(201).build();
    }
}


