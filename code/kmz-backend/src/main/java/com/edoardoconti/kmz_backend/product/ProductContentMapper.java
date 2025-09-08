package com.edoardoconti.kmz_backend.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductContentMapper {
    ProductContentDto toDto(ProductContent productContent);
    ProductContent toEntity(ProductContentDto productContentDto);
}
