package com.edoardoconti.kmz_backend.user;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDto);
    User toGenericUserEntity(UserDTO userDto);
}