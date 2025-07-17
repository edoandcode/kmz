package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.content.Content;

import java.util.List;

public interface ProductService {
    void uploadProduct(Content content);
    List<Content> getProducts();
    Content getProduct(Long id);
}
