package com.edoardoconti.kmz_backend.user;

import java.util.List;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    UserDto getUser(Long id);
    List<UserDto> getUsers();
}
