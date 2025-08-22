package com.edoardoconti.kmz_backend.user;

import com.edoardoconti.kmz_backend.role.UserRoleType;
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
    public UserDTO toDto(User user) {
        return this.delegate.toDto(user);
    }

    @Override
    public User toEntity(UserDTO userDto) {
        return this.delegate.toEntity(userDto);
    }

    @Override
    public User toGenericUserEntity(UserDTO userDto) {
        User user = this.toEntity(userDto);
        user.setRoles(Set.of(UserRoleType.GENERIC_USER));
        return user;
    }
}
