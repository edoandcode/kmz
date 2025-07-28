package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealUserService implements UserService{

    private UserRegister userRegister;
    private final List<User> userRepository = new ArrayList<>();
    private Long nextId = 1L;



    @Override
    public void signUp(User user, UserRole[] roles) {
        this.createGenericUser(user);
        this.requestRolePermission(user, roles);
    }

    @Override
    public List<User> getUsers() {
        return userRepository;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.
                stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // private methods

    private void createGenericUser(User user) {
        var genericUser = new User(
                user.getFirstName(),
                user.getLastName()
        );
        genericUser.setId(nextId++);
        genericUser.addRole(new GenericUserRole());
        userRepository.add(genericUser);
    }

    private void requestRolePermission(User user, UserRole ...roles) {
        for (UserRole role : roles) {
            userRegister.addRequest(user, role);
        }
    }

}
