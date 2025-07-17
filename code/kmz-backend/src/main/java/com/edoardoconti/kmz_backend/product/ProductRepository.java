package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Content> products = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Content content) {
        if(content.getType() != ContentType.PRODUCT)
            return;
        content.setId(nextId++);
        products.add(content);
    }

    public List<Content> getProducts() {
        return products;
    }


    public Content getProduct(Long id) {
        return products.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
}
