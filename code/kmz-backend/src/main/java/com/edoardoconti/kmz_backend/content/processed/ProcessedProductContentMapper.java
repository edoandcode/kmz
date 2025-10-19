package com.edoardoconti.kmz_backend.content.processed;

import com.edoardoconti.kmz_backend.content.process.ProcessContentMapper;
import com.edoardoconti.kmz_backend.content.product.ProductContentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProcessContentMapper.class, ProductContentMapper.class })
public interface ProcessedProductContentMapper {
    ProcessedProductContentDto toDto(ProcessedProductContent entity);
    ProcessedProductContent toEntity(ProcessedProductContentDto dto);
}
