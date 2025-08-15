package com.edoardoconti.kmz_backend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealProductService implements ProductService{

    private final ProductRepository repository;

    @Override
    public Product uploadProduct(Product product) {
        return this.repository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return this.repository.findById(id).orElse(null);
    }
}
