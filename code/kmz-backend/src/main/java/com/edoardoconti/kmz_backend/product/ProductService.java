package com.edoardoconti.kmz_backend.product;

import java.util.List;

public interface ProductService {
    Product uploadProduct(Product product);
    List<Product> getProducts();
    Product getProduct(Long id);
}
