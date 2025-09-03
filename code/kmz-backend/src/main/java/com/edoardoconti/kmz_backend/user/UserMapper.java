package com.edoardoconti.kmz_backend.user;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toGenericUserEntity(UserRegisterDto userRegisterDto);
}