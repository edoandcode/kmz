package com.edoardoconti.kmz_backend.content.product;

import com.edoardoconti.kmz_backend.content.Content;

import java.util.List;

public interface ProductService {
    ProductContentDto uploadProduct(ProductContentDto productContentDto);
    List<ProductContentDto> getProducts();
    List<ProductContentDto> getPublicProducts();
    ProductContentDto getProduct(Long id);
    List<ProductContentDto> getMyProducts();
    ProductContentDto getMyProduct(Long id);
    ProductContentDto deleteProduct(Long id);
}
