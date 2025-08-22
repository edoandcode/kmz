package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public void addSignUpRequest(Long userId, Set<UserRoleType> requestedRoles) {
        for(var role : requestedRoles)
            userRequestRepository.save(new UserSignUpRequest(userId, role));
    }

    public void approveRequest(Long requestId) {
        var request = this.getRequest(requestId);
        request.approve();
        userRequestRepository.save(request);
    }

    public void rejectRequest(Long requestId) {
        var request = this.getRequest(requestId);
        request.reject();
        userRequestRepository.save(request);
    }


    public UserSignUpRequest getRequest(Long requestId) {
        var request = userRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new NoSuchElementException("Request with id " + requestId + " was not found.");
        return request;
    }

    public List<UserSignUpRequest> getRequests(RequestStatus status, Long userId) {
        return userRequestRepository.findAll().stream()
                .filter(r -> status == null || r.getStatus().equals(status))
                .filter(r -> userId == null || r.getUserId().equals(userId))
                .toList();
    }

}
