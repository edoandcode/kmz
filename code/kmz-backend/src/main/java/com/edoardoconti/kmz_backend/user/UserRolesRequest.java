package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="signup_requests")
public final class UserRolesRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private UserRoleType requestedRole;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date processedAt;

    public UserRolesRequest(Long userId, UserRoleType requestedRole) {
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
