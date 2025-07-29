package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserRegisterService {
    private UserRequestRepository userRequestRepository;

    public void addRequest(Long userId, UserRoleType requestedRole) {
        userRequestRepository.save(new UserSignUpRequest(userId, requestedRole));
    }

    public void processRequest(Long requestId, RequestStatus status) {
        var request = userRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new NoSuchElementException("Request with id " + requestId + " was not found.");
        request.processRequest(status);
    }

    public List<UserSignUpRequest> getPendingRequests() {
        return userRequestRepository.findByStatus(RequestStatus.PENDING);
    }
}
