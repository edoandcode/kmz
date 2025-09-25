package com.edoardoconti.kmz_backend.content.event;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventContentMapper {
    EventContentDto toDto(EventContent event);
    EventContent toEntity(EventContentDto dto);
}
