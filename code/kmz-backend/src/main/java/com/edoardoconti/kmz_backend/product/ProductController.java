package com.edoardoconti.kmz_backend.product;

import java.util.List;

import com.edoardoconti.kmz_backend.content.Content;
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
    public ResponseEntity<List<Content>> getProducts() {
        return ResponseEntity.ok(this.service.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<Void> uploadProduct(@RequestBody Content content) {
        this.service.uploadProduct(content);
        return ResponseEntity.status(201).build();
    }


}
