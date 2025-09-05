package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final UserRequestRepository userRequestRepository;
    private final UserRepository userRepository;

    public void addSignUpRequest(Long userId, Set<UserRoleType> requestedRoles) {
        for(var role : requestedRoles)
            if(!role.equals(UserRoleType.GENERIC_USER))
                this.userRequestRepository.save(new UserRegisterRequest(userId, role));
    }

    public void approveRequest(Long requestId) {
        var request = this.getRequest(requestId);
        request.approve();
        this.userRequestRepository.save(request);
        var user = this.userRepository.findById(request.getUserId()).orElseThrow();
        user.addRole(request.getRequestedRole());
        this.userRepository.save(user);
    }

    public void rejectRequest(Long requestId) {
        var request = this.getRequest(requestId);
        request.reject();
        this.userRequestRepository.save(request);
    }


    public UserRegisterRequest getRequest(Long requestId) {
        var request = userRequestRepository.findById(requestId).orElse(null);
        if(request == null)
            throw new NoSuchElementException("Request with id " + requestId + " was not found.");
        return request;
    }

    public List<UserRegisterRequest> getRequests(RequestStatus status, Long userId) {
        return userRequestRepository.findAll().stream()
                .filter(r -> status == null || r.getStatus().equals(status))
                .filter(r -> userId == null || r.getUserId().equals(userId))
                .toList();
    }

}
