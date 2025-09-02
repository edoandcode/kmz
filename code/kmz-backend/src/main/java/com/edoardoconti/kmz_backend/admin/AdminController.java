package com.edoardoconti.kmz_backend.admin;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.user.UserRequestService;
import com.edoardoconti.kmz_backend.user.UserSignUpRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name="Admin")
public class AdminController {

    private final UserRequestService userRequestService;

    public AdminController(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    @GetMapping("/requests")
    public List<UserSignUpRequest> getRequests(
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
}
