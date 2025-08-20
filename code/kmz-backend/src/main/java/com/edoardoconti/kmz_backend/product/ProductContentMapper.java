package com.edoardoconti.kmz_backend.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductContentMapper {
    ProductContentDTO toDto(ProductContent productContent);
    ProductContent toEntity(ProductContentDTO productContentDto);
}
