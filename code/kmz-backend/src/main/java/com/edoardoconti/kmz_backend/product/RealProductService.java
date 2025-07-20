package com.edoardoconti.kmz_backend.product;

import com.edoardoconti.kmz_backend.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealProductService implements ProductService{

    private ProductRepository repository;

    public RealProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void uploadProduct(Product product) {
        this.repository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.repository.getProducts();
    }

    @Override
    public Product getProduct(Long id) {
        return this.repository.getProduct(id);
    }
}
