package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.Getter;

import java.util.Date;

@Getter
public final class UserRequest {
    private final User user;
    private final UserRole role;
    private RequestStatus status;
    private final Date requestedAt;
    private Date processedAt;

    public UserRequest(User user, UserRole role) {
        this.user = user;
        this.role = role;
        this.status = RequestStatus.PENDING;
        this.requestedAt = new Date();
    }

    public void processRequest(RequestStatus status) {
        if(processedAt != null)
            throw new IllegalStateException("This request has already been processed");
        this.status = status;
        processedAt = new Date();
    }
}
