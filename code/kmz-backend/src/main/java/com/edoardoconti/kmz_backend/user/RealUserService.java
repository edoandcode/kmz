package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.request.RequestService;
import com.edoardoconti.kmz_backend.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RealUserService implements UserService{

    private final RequestService requestService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


     @Override
    public boolean superAdminExists() {
        return this.userRepository.existsByRolesContaining(UserRole.ADMINISTRATOR);
     }

    @Override
    public void registerSuperAdmin(UserRegisterDto userRegisterDto) {
        if(this.userRepository.existsByRolesContaining(UserRole.ADMINISTRATOR))
            throw new IllegalStateException("Super Admin user already exists");
        var newUser = this.userMapper.toEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.addRole(UserRole.ADMINISTRATOR);
        this.userRepository.save(newUser);
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if(this.userRepository.findByEmail(userRegisterDto.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already in use");
        User newUser = this.userMapper.toGenericUserEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        this.userRepository.save(newUser);
        for (var role : userRegisterDto.getRoles()) {
            this.requestService.createUserRegistrationRequest(newUser, role);
        }
        System.out.println("New user" + newUser);
    }


    @Override
    public void addUserRole(Long userId, UserRole role) {
        var user = this.userRepository.findById(userId).orElse(null);
        if(user == null)
            throw new IllegalArgumentException("User not found");
        user.addRole(role);
        this.userRepository.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(this.userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUser(Long id) {
        var user = this.userRepository.findById(id).orElse(null);
        return this.userMapper.toDto(user);
    }
}
