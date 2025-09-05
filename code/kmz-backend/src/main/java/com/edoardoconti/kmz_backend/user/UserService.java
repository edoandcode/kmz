package com.edoardoconti.kmz_backend.user;

import java.util.List;

public interface UserService {
    void registerAdmin(UserDTO user);
    void signUp(UserDTO user);
    UserDTO getUser(Long id);
    List<UserDTO> getUsers();
}
