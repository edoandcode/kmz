package com.edoardoconti.kmz_backend.request.controllers;

import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.request.RequestStatus;
import com.edoardoconti.kmz_backend.request.requests.UserRegistrationRequest;
import com.edoardoconti.kmz_backend.request.requests.UserRegistrationResponseDto;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/requests/users")
public class UserRequestController {

    private final RequestService requestService;
    private final UserService userService;



    @GetMapping("/registration")
    public List<UserRegistrationResponseDto> getUserRegistrationRequests(
            @RequestParam(value = "status", required = false) RequestStatus status,
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        var usersRequests = this.requestService.getUserRegistrationRequests();
        if (status != null)
            usersRequests = usersRequests.stream().filter(r -> r.getStatus() == status).toList();
        if (userId != null)
            usersRequests = usersRequests.stream().filter(r -> r.getUserId().equals(userId)).toList();
        return usersRequests;
    }

    @PostMapping("/approve/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            var request = this.requestService.getUserRegistrationRequests()
                    .stream()
                    .filter(r -> r.getId().equals(requestId))
                    .findFirst()
                    .orElse(null);
            if (request == null)
                return ResponseEntity.badRequest().body("Request not found.");
            this.userService.addUserRole(request.getUserId(), request.getRequestedRole());
            this.requestService.approve(requestId, message);
            return ResponseEntity.ok().body("Request approved successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to approve request: " + ex.getMessage());
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
           this.requestService.reject(requestId, message);
            return ResponseEntity.ok().body("Request rejected successfully.");
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Failed to reject request: " + ex.getMessage());
        }
    }

}
