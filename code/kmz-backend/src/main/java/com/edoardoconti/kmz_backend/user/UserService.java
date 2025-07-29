package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.role.UserRoleType;

import java.util.List;

public interface UserService {
    void signUp(UserDTO user, List<UserRoleType> roles);
    User getUser(Long id);
    List<User> getUsers();
}
