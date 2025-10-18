package com.edoardoconti.kmz_backend.request.user_registration;

import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.request.RequestStatus;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/requests/users")
public class UserRegistrationRequestController {

    private final UserRegistrationRequestService userRegistrationRequestService;
    private final UserService userService;



    @GetMapping("/registration")
    public ResponseEntity<List<UserRegistrationResponseDto>> getUserRegistrationRequests(
            @RequestParam(value = "status", required = false) RequestStatus status,
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        var usersRequests = this.userRegistrationRequestService.getUserRegistrationRequests();
        if (status != null)
            usersRequests = usersRequests.stream().filter(r -> r.getStatus() == status).toList();
        if (userId != null)
            usersRequests = usersRequests.stream().filter(r -> r.getUserId().equals(userId)).toList();
        return ResponseEntity.ok().body(usersRequests);
    }

    @GetMapping("/registration/me")
    public ResponseEntity<List<UserRegistrationResponseDto>> getMyUserRegistrationRequests() {
        return ResponseEntity.ok().body(this.userRegistrationRequestService.getMyUserRegistrationRequests());
    }

    @PostMapping("/approve/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.userRegistrationRequestService.approveRequest(requestId, message);
            return ResponseEntity.ok().body("Request approved successfully.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Failed to approve request: " + ex.getMessage());
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId, @RequestBody(required = false) String message) {
        try {
            this.userRegistrationRequestService.rejectRequest(requestId, message);
            return ResponseEntity.ok().body("Request rejected successfully.");
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Failed to reject request: " + ex.getMessage());
        }
    }

}
