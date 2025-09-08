package com.edoardoconti.kmz_backend.process;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessContentMapper {
    ProcessContentDto toDto(ProcessContent processContent);
    ProcessContent toEntity(ProcessContentDto processContentDto);
}
