package com.edoardoconti.kmz_backend.content.product;

import java.util.List;

public interface ProductService {
    ProductContentDto uploadProduct(ProductContentDto productContentDto);
    List<ProductContentDto> getProducts();
    ProductContentDto getProduct(Long id);
    List<ProductContentDto> getMyProducts();
    ProductContentDto getMyProduct(Long id);
}
