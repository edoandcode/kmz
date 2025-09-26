package com.edoardoconti.kmz_backend.request.user_registration;

import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.user.User;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserRegistrationRequestService extends RequestService {
    private final UserRegistrationRequestRepository userRegistrationRequestRepository;
    private final UserService userService;


    public List<UserRegistrationResponseDto> getUserRegistrationRequests() {
        var requests = this.userRegistrationRequestRepository.findAll();
        return requests.stream().map(UserRegistrationMapper::toDto).toList();
    }


    @Override
    public void approveRequest(Long requestId, String message) {
        var request = this.userRegistrationRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        this.userService.addUserRole(request.getUser().getId(), request.getRequestedRole());
        var approvedRequest = this.getApprovedRequest(request, message);
        this.userRegistrationRequestRepository.save(approvedRequest);
    }

    @Override
    public void rejectRequest(Long requestId, String message) {
        var request = this.userRegistrationRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new IllegalArgumentException("Request not found");
        var rejectedRequest = this.getRejectedRequest(request, message);
        this.userRegistrationRequestRepository.save(rejectedRequest);
    }
}
