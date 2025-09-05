package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RealUserService implements UserService{

    private final UserRequestService userRequestService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void registerAdmin(UserDTO userDto) {
        var user = this.userMapper.toEntity(userDto);
        user.addRole(UserRoleType.ADMINISTRATOR);
        this.userRepository.save(user);
    }

    @Override
    public void signUp(UserDTO user) {
        User newUser = this.userMapper.toGenericUserEntity(user);
        this.userRepository.save(newUser);
        System.out.println("User roles " + user.getRoles());
        this.userRequestService.addSignUpRequest(newUser.getId(), user.getRoles());
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
}
