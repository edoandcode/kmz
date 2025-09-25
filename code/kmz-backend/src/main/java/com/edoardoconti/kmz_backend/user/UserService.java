package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserRegisterDto userRegisterDto);
    UserDto getUser(Long id);
    List<UserDto> getUsers();
    UserDto registerSuperAdmin(UserRegisterDto userRegisterDto);
    void addUserRole(Long userId, UserRole role);
    boolean superAdminExists();
}
