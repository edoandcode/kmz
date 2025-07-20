package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.content.ContentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Product product) {
        if(product.getType() != ContentType.PRODUCT)
            return;
        product.setId(nextId++);
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }


    public Product getProduct(Long id) {
        return products.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
}
