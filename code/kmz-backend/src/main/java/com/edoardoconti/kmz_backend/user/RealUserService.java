package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.GenericUserRole;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealUserService implements UserService{

    private final UserRegister userRegister;
    private final List<User> userRepository = new ArrayList<>();
    private Long nextId = 1L;



    @Override
    public void signUp(User user, List<UserRole> roles) {
        this.createGenericUser(user);
        this.requestRolePermission(user, roles);
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository;
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.
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
        this.userRepository.add(genericUser);
    }

    private void requestRolePermission(User user, List<UserRole> roles) {
        for (UserRole role : roles) {
            this.userRegister.addRequest(user, role);
        }
    }

}
