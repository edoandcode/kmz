package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;

import java.util.List;

public interface UserService {
    void signUp(User user, UserRole[] roles);
    List<User> getUsers();
    User getUser(Long id);
}
