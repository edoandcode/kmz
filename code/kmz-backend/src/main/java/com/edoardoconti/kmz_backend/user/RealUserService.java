package com.edoardoconti.kmz_backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealUserService implements UserService{

    private final UserRequestService userRequestService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User newUser = this.userMapper.toGenericUserEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        this.userRepository.save(newUser);
        this.userRequestService.addSignUpRequest(newUser.getId(), userRegisterDto.getRoles());
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
