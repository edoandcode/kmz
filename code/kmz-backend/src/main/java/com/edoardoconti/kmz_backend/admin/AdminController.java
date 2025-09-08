package com.edoardoconti.kmz_backend.admin;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.user.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@Tag(name="Admin")
public class AdminController {

    private final UserRequestService userRequestService;
    private final UserService userService;


    @GetMapping("/requests")
    public List<UserRegisterRequest> getRequests(
            @RequestParam(value = "status", required = false) RequestStatus status,
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        return this.userRequestService.getRequests(status, userId);
    }

    @PostMapping("/approve/{requestId}")
    public void approveRequest(@PathVariable Long requestId) {
        this.userRequestService.approveRequest(requestId);
    }

    @PostMapping("/reject/{requestId}")
    public void rejectRequest(@PathVariable Long requestId) {
        this.userRequestService.rejectRequest(requestId);
    }

    @PostMapping("setup")
    public ResponseEntity<UserDto> registerAdmin(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        this.userService.registerAdmin(userRegisterDto);
        return ResponseEntity.status(201).build();
    }
}
