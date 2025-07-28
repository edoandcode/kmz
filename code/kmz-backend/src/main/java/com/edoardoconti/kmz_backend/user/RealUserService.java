package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;
import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealUserService implements UserService{

    private final UserRegister userRegister;
    private final UserRepository userRepository;
    private Long nextId = 1L;

    @Override
    public void signUp(UserDTO user, List<UserRoleType> roles) {
        User savedUser = this.createGenericUser(user);
        this.requestRolePermission(savedUser, roles);
        this.userRepository.save(savedUser);
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
                user.getFirstName(),
                user.getLastName()
        );
        genericUser.setId(nextId++);
        genericUser.addRole(UserRoleType.GENERIC_USER);
        return genericUser;
    }

    private void requestRolePermission(User user, List<UserRoleType> roles) {
        for (UserRoleType role : roles) {
            this.userRegister.addRequest(user, role.create());
        }
    }

}
