package com.edoardoconti.kmz_backend.request.user_registration;

import com.edoardoconti.kmz_backend.request.Request;
import com.edoardoconti.kmz_backend.request.RequestType;
import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.user.User;
import com.edoardoconti.kmz_backend.user.UserDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name="registration_requests")
public class UserRegistrationRequest  extends Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message="user are required")
    @Valid
    @ManyToOne
    private User user;

    @NotNull(message="role is required")
    @Valid
    @Enumerated(EnumType.STRING)
    private UserRole requestedRole;

    protected UserRegistrationRequest() {
        super(RequestType.USER_REGISTRATION, null);
    }

    public UserRegistrationRequest(User user, UserRole role) {
        super(RequestType.USER_REGISTRATION, null);
        this.user = user;
        this.requestedRole = role;
    }
}
