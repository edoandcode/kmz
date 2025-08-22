package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.common.RequestStatus;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="signup_requests")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserSignUpRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message="user id data are required")
    @Valid
    private Long userId;

    @NotNull(message="role is required")
    @Valid
    @Enumerated(EnumType.STRING)
    private UserRoleType requestedRole;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date processedAt;


    public UserSignUpRequest(Long userId, UserRoleType role) {
        this.userId = userId;
        this.status = RequestStatus.PENDING;
        this.requestedRole = role;
        this.requestedAt = new Date();
    }

    public void approve() {
        if(!this.status.equals(RequestStatus.PENDING))
            throw new IllegalStateException("This request has already been processed");
        this.status = RequestStatus.APPROVED;
        this.processedAt = new Date();
    }

    public void reject() {
        if(!this.status.equals(RequestStatus.PENDING))
            throw new IllegalStateException("This request has already been processed");
        this.status = RequestStatus.REJECTED;
        this.processedAt = new Date();
    }
}
