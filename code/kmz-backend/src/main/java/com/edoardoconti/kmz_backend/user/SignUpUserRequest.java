package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SignUpUserRequest {
    @NotNull(message="User data are required")
    @Valid
    private UserDTO user;
    private List<UserRoleType> roles;
}
