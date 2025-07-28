package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.Data;

import java.util.List;
import java.util.Queue;

@Data
public class UserRegister {
    private Queue<UserRequest> pendingRequests;

    public void addRequest(User user, UserRole role) {
        pendingRequests.add(new UserRequest(user, role));
    }

    public void processRequest(RequestStatus status) {
        var request = pendingRequests.poll();
        request.processRequest(status);
    }


    public UserRequest getNextRequest() {
        return pendingRequests.peek();
    }

    public List<UserRequest> getPendingRequests() {
        return pendingRequests.stream().toList();
    }
}
