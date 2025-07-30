package com.edoardoconti.kmz_backend.admin;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.user.UserRegisterService;
import com.edoardoconti.kmz_backend.user.UserSignUpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRegisterService userRegisterService;

    public AdminController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @GetMapping("/requests")
    public List<UserSignUpRequest> getRequests(@RequestParam(value = "status", required = false) RequestStatus status) {
        return this.userRegisterService.getRequests(status);
    }

    @PostMapping("/approve/{requestId}")
    public void approveRequest(@PathVariable Long requestId) {
        this.userRegisterService.processRequest(requestId, RequestStatus.APPROVED);
    }

    @PostMapping("/reject/{requestId}")
    public void rejectRequest(@PathVariable Long requestId) {
        this.userRegisterService.processRequest(requestId, RequestStatus.REJECTED);
    }



}
