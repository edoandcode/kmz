package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRequestRepository userRequestRepository;

    public void addRequest(Long userId, UserRoleType requestedRole) {
        userRequestRepository.save(new UserSignUpRequest(userId, requestedRole));
    }

    public void processRequest(Long requestId, RequestStatus status) {
        var request = userRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new NoSuchElementException("Request with id " + requestId + " was not found.");
        request.processRequest(status);
        userRequestRepository.save(request);
    }

    public List<UserSignUpRequest> getRequests(RequestStatus status) {
        if(status != null)
            return userRequestRepository.findByStatus(status);
        return userRequestRepository.findAll();
    }
}
