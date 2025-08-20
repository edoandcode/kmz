package com.edoardoconti.kmz_backend.event;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventContentMapper {
    EventContentDTO toDto(EventContent event);
    EventContent toEntity(EventContentDTO dto);
}
