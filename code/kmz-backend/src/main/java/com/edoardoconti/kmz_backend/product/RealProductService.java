package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.content.Content;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealProductService implements ProductService{

    private ProductRepository repository;

    public RealProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void uploadProduct(Content content) {
        this.repository.save(content);
    }

    @Override
    public List<Content> getProducts() {
        return this.repository.getProducts();
    }

    @Override
    public Content getProduct(Long id) {
        return this.repository.getProduct(id);
    }
}
