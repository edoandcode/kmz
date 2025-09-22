package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapperDecorator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Override
    public UserDto toDto(User user) {
        return this.delegate.toDto(user);
    }

    @Override
    public User toEntity(UserDto userDto) {
        return this.delegate.toEntity(userDto);
    }

    @Override
    public User toEntity(UserRegisterDto userRegisterDto) {
        return this.delegate.toEntity(userRegisterDto);
    }

    @Override
    public User toGenericUserEntity(UserRegisterDto userRegisterDto) {
        var newUser = new User();
        newUser.setFirstName(userRegisterDto.getFirstName());
        newUser.setLastName(userRegisterDto.getLastName());
        newUser.setEmail(userRegisterDto.getEmail());
        newUser.setPassword(userRegisterDto.getPassword());
        newUser.setRoles(Set.of(UserRole.GENERIC_USER));
        return newUser;
    }
}
