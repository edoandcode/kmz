package com.edoardoconti.kmz_backend.content.processed;

import java.util.List;

public interface ProcessedProductService {
    ProcessedProductContentDto uploadProcessedProduct(ProcessedProductContentDto productContentDto);
    List<ProcessedProductContentDto> getProcessedProducts();
    ProcessedProductContentDto getProcessedProduct(Long id);
    List<ProcessedProductContentDto> getMyProcessedProducts();
    ProcessedProductContentDto getMyProcessedProduct(Long id);
    ProcessedProductContentDto deleteProcessedProduct(Long id);
}
