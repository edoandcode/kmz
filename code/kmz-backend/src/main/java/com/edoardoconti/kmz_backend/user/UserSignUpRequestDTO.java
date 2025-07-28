package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRoleType;

public record UserSignUpRequestDTO(UserDTO user, UserRoleType[] userRoles) {
}
