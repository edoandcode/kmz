package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.product.Product;

import java.util.List;

public interface ProductService {
    void uploadProduct(Product product);
    List<Product> getProducts();
    Product getProduct(Long id);
}
