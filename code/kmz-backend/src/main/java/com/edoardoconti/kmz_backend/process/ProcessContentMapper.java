package com.edoardoconti.kmz_backend.process;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessContentMapper {
    ProcessContentDTO toDto(ProcessContent processContent);
    ProcessContent toEntity(ProcessContentDTO processContentDto);
}
