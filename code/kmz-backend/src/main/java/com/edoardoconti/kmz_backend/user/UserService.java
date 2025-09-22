package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    UserDto getUser(Long id);
    List<UserDto> getUsers();
    void registerAdmin(UserRegisterDto userRegisterDto);
    void addUserRole(Long userId, UserRole role);
}
