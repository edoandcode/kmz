package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealUserService implements UserService{

    private final UserRegisterService userRegisterService;
    private final UserRepository userRepository;

    @Override
    public void signUp(UserDTO user, List<UserRoleType> roles) {
        User newUser = this.createGenericUser(user);
        User userEntity =  this.userRepository.save(newUser);
        this.requestRolePermission(userEntity.getId(), roles);
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    // private methods

    private User createGenericUser(UserDTO user) {
        var genericUser = new User(
                user.firstName(),
                user.lastName()
        );
        genericUser.addRole(UserRoleType.GENERIC_USER);
        return genericUser;
    }

    private void requestRolePermission(Long userId, List<UserRoleType> roles) {
        for (UserRoleType role : roles) {
            this.userRegisterService.addRequest(userId, role);
        }
    }

}
