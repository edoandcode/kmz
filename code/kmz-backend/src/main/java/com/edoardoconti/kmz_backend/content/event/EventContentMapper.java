package com.edoardoconti.kmz_backend.content.event;

import com.edoardoconti.kmz_backend.user.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface EventContentMapper {
    EventContentDto toDto(EventContent event);
    EventContent toEntity(EventContentDto dto);
}
