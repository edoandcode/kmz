package com.edoardoconti.kmz_backend.product;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(this.service.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<Void> uploadProduct( @RequestBody Product product) {
        this.service.uploadProduct(product);
        return ResponseEntity.status(201).build();
    }


}
