package com.edoardoconti.kmz_backend.content.product;

import com.edoardoconti.kmz_backend.content.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductContentDto>> getProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductContentDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProduct(id));
    }

    @GetMapping("/products/me")
    public ResponseEntity<List<ProductContentDto>> getMyProducts() {
        return ResponseEntity.ok(this.productService.getMyProducts());
    }

    @GetMapping("/products/me/{id}")
    public ResponseEntity<ProductContentDto> getMyProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getMyProduct(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductContentDto> uploadProduct(@RequestBody @Valid ProductContentDto productContentDto) {
        var savedProduct = this.productService.uploadProduct(productContentDto);
        // contentRequestService.addRequest(savedProduct.getAuthorId(), savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductContentDto> deleteProduct(@PathVariable Long id) {
        var deletedContent = this.productService.deleteProduct(id);
        if(deletedContent == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedContent);
    }
}
