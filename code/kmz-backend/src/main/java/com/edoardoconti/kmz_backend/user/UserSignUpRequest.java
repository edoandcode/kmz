package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public final class UserSignUpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private UserRoleType requestedRole;
    private RequestStatus status;
    private final Date requestedAt;
    private Date processedAt;

    public UserSignUpRequest(Long userId, UserRoleType requestedRole) {
        this.userId = userId;
        this.requestedRole = requestedRole;
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
