package com.edoardoconti.kmz_backend.product;

import java.util.List;

public interface ProductService {
    ProductContentDTO uploadProduct(ProductContentDTO productContentDto);
    List<ProductContentDTO> getProducts();
    ProductContentDTO getProduct(Long id);
}
