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
    private final UserMapper userMapper;

    @Override
    public void signUp(SignUpUserRequest request) {
        User newUser = this.userMapper.toEntity(request.getUser());
        newUser.addRole(UserRoleType.GENERIC_USER);
        this.userRepository.save(newUser);
        this.requestRolePermission(newUser.getId(), request.getRoles());
    }

    @Override
    public List<UserDTO> getUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(this.userMapper::toDto)
                .toList();
    }

    @Override
    public UserDTO getUser(Long id) {
        var user = this.userRepository.findById(id).orElse(null);
        return this.userMapper.toDto(user);
    }

    // private methods

    private void requestRolePermission(Long userId, List<UserRoleType> roles) {
        for (UserRoleType role : roles) {
            this.userRegisterService.addRequest(userId, role);
        }
    }

}
