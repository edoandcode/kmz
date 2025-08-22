package com.edoardoconti.kmz_backend.user;

import java.util.List;

public interface UserService {
    void signUp(UserDTO user);
    UserDTO getUser(Long id);
    List<UserDTO> getUsers();
}
